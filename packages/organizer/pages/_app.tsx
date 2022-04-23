import '../styles/globals.css';
import type { AppProps } from 'next/app';

import { Counter } from '@organizer/common';

function MyApp({ Component, pageProps }: AppProps) {
  return <Counter />;

  return <Component {...pageProps} />;
}

export default MyApp;
