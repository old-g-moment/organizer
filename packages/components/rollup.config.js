import resolve from '@rollup/plugin-node-resolve';
import commonjs from '@rollup/plugin-commonjs';
import typescript from '@rollup/plugin-typescript';
import { terser } from 'rollup-plugin-terser';
import peerDepsExternal from 'rollup-plugin-peer-deps-external';
import tailwind from 'rollup-plugin-tailwindcss';
import postcss from 'rollup-plugin-postcss';
import generateDeclarations from 'rollup-plugin-generate-declarations';

const packageJson = require('./package.json');

export default [
  {
    input: 'src/index.ts',
    output: [
      {
        file: packageJson.main,
        format: 'cjs',
        sourcemap: true,
      },
      {
        file: packageJson.module,
        format: 'esm',
        sourcemap: true,
      },
    ],
    plugins: [
      peerDepsExternal(),
      tailwind({
        input: './src/styles.css', // required
        // Tailor the emitted stylesheet to the bundle by removing any unused CSS
        // (highly recommended when packaging for distribution).
        purge: false,
      }),
      postcss({
        config: {
          path: './postcss.config.js',
        },
        extensions: ['.css'],
        minimize: true,
        inject: {
          insertAt: 'top',
        },
      }),
      ,
      resolve(),
      commonjs(),
      typescript({ tsconfig: './tsconfig.json' }),
      generateDeclarations(),
      terser(),
    ],
    external: ['react', 'react-dom'],
  },
  {
    input: 'src/Button/index.ts',
    output: [
      {
        file: 'lib/Button/index.js',
        format: 'cjs',
        sourcemap: true,
      },
      {
        file: 'lib/Button/index-esm.js',
        format: 'esm',
        sourcemap: true,
      },
    ],
    plugins: [
      peerDepsExternal(),
      tailwind({
        input: './src/styles.css', // required
        // Tailor the emitted stylesheet to the bundle by removing any unused CSS
        // (highly recommended when packaging for distribution).
        purge: false,
      }),
      postcss({
        config: {
          path: './postcss.config.js',
        },
        extensions: ['.css'],
        minimize: true,
        inject: {
          insertAt: 'top',
        },
      }),
      ,
      resolve(),
      commonjs(),
      typescript({ tsconfig: './tsconfig.json' }),
      generateDeclarations(),
      terser(),
    ],
    external: ['react', 'react-dom'],
  },
];
