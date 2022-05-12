import React from 'react';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import SaveIcon from '@mui/icons-material/Save';

import { Button, Variant } from './Button';

const testButtonVariantToHaveClassName = (variant: Variant, className: string) => {
  render(<Button content="Custom variant button" variant={variant} />);
  const button = screen.getByRole('button', { name: /custom variant button/i });
  expect(button).toBeVisible();
  expect(button).toHaveClass(className);
};

describe('Button component tests', () => {
  it('Renders button content as text', () => {
    render(<Button content="Hello tests" />);
    expect(screen.getByRole('button', { name: /hello tests/i })).toBeVisible();
  });

  it('Renders button content as icon', () => {
    render(<Button content={<SaveIcon />} />);
    expect(screen.getByRole('button')).toBeVisible();
    expect(screen.getByTestId('SaveIcon')).toBeVisible();
  });

  it('Renders button with custom class', () => {
    render(<Button content="Custom class button" className="p-10 bg-red-500" />);
    const button = screen.getByRole('button', { name: /custom class button/i });
    expect(button).toBeVisible();
    expect(button).toHaveClass('p-10 bg-red-500');
  });

  it('Renders button with default variant', () => {
    testButtonVariantToHaveClassName('default', 'bg-slate-700');
  });

  it('Renders button with disabled variant', () => {
    testButtonVariantToHaveClassName('disabled', 'bg-gray-300');
  });

  it('Renders button with error variant', () => {
    testButtonVariantToHaveClassName('error', 'bg-red-600');
  });

  it('Renders button with info variant', () => {
    testButtonVariantToHaveClassName('info', 'bg-blue-600');
  });

  it('Renders button with warning variant', () => {
    testButtonVariantToHaveClassName('warning', 'bg-orange-500');
  });

  it('Renders button with success variant', () => {
    testButtonVariantToHaveClassName('success', 'bg-green-500');
  });

  it('Renders button with loading state', () => {
    render(<Button content="Loading button" loading />);
    expect(screen.getByRole('button', { name: /loading.../i })).toBeVisible();
    const saveIcon = screen.getByTestId('SaveIcon');
    expect(saveIcon).not.toBeVisible();
    expect(saveIcon).toBeInTheDocument();
  });

  it('Renders button with click action', async () => {
    const clickAction = jest.fn();
    render(<Button content="Click" onClick={clickAction} />);
    const button = screen.getByRole('button', { name: /click/i });
    expect(button).toBeVisible();
    await userEvent.click(button);
    expect(clickAction).toHaveBeenCalled();
  });

  it('Renders button with type', async () => {
    render(<Button content="Type" type="submit" />);
    const button = screen.getByRole('button', { name: /type/i });
    expect(button).toBeVisible();
    expect(button).toHaveAttribute('type', 'submit');
  });

  it('Renders button with custom styles', async () => {
    render(<Button content="Custom styles" style={{ border: '2px solid red' }} />);
    const button = screen.getByRole('button', { name: /custom styles/i });
    expect(button).toBeVisible();
    expect(button).toHaveStyle({ border: '2px solid red' });
  });

  it('Renders button with size', async () => {
    render(<Button content="Size" size="large" />);
    const button = screen.getByRole('button', { name: /size/i });
    expect(button).toBeVisible();
    expect(button).toHaveClass('MuiButton-sizeLarge');
  });

  it('Renders button with disabled state', async () => {
    render(<Button content="Disabled" disabled />);
    const button = screen.getByRole('button', { name: /disabled/i });
    expect(button).toBeVisible();
    expect(button).toBeDisabled();
    expect(button).toHaveClass('bg-gray-300 text-gray-400');
  });

  it('Renders button with start icon', async () => {
    const { asFragment } = render(<Button content="Start icon" startIcon={<SaveIcon />} />);
    expect(screen.getByRole('button', { name: /start icon/i })).toBeVisible();
    expect(asFragment()).toMatchSnapshot();
  });

  it('Renders button with end icon', async () => {
    const { asFragment } = render(<Button content="End icon" endIcon={<SaveIcon />} />);
    expect(screen.getByRole('button', { name: /end icon/i })).toBeVisible();
    expect(asFragment()).toMatchSnapshot();
  });
});
