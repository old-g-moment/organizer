import React, { ComponentProps, CSSProperties, ReactNode } from 'react';
import LoadingButton from '@mui/lab/LoadingButton';
import ButtonMui from '@mui/material/Button';
import SaveIcon from '@mui/icons-material/Save';

export type Variant = 'success' | 'warning' | 'info' | 'error' | 'default' | 'disabled';

export type Size = 'small' | 'medium' | 'large';

export const variantMap: Record<Variant, string> = {
  error: 'bg-red-600 text-slate-50',
  info: 'bg-blue-600 text-slate-50',
  warning: 'bg-orange-500 text-slate-50',
  success: 'bg-green-500 text-slate-50',
  default: 'bg-slate-700 text-slate-50',
  disabled: 'bg-gray-300 text-gray-400',
};

export type Props = {
  loading?: boolean;
  content: ReactNode;
  className?: string;
  variant?: Variant;
  onClick?: () => void;
  type?: ComponentProps<'button'>['type'];
  style?: CSSProperties;
  size?: Size;
  startIcon?: ReactNode;
  endIcon?: ReactNode;
  disabled?: boolean;
};

export const Button = ({
  content,
  className = '',
  loading = false,
  onClick,
  variant = 'default',
  type = 'button',
  style = {},
  size = 'medium',
  startIcon = null,
  endIcon = null,
  disabled = false,
}: Props) => {
  const classNames = [disabled ? variantMap.disabled : variantMap[variant], className].join(' ');

  if (loading)
    return (
      <LoadingButton
        type={type}
        variant="contained"
        className={classNames}
        style={style}
        size={size}
        loading
        loadingPosition="start"
        startIcon={<SaveIcon />}
      >
        Loading...
      </LoadingButton>
    );

  return (
    <ButtonMui
      type={type}
      variant="contained"
      className={classNames}
      style={style}
      size={size}
      disabled={disabled}
      onClick={onClick}
      startIcon={startIcon}
      endIcon={endIcon}
    >
      {content}
    </ButtonMui>
  );
};
