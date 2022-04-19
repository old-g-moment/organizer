import React from 'react';
import { ComponentStory, ComponentMeta } from '@storybook/react';
import SaveIcon from '@mui/icons-material/Save';

import { Button } from './Button';

export default {
  title: 'Button',
  component: Button,
  parameters: {
    docs: {
      description: {
        component: 'Component that will be used everywhere and act as a button',
      },
    },
  },
  argTypes: {
    content: {
      description: 'Content to be displayed inside the button',
    },
    loading: {
      description: 'Indicates whether to show a loader or not',
    },
    className: {
      description: 'A custom tailwind class to be applied on an element',
    },
    variant: {
      description: 'Shows to the user a different button styles depending on the passed variant',
    },
    onClick: {
      description: 'Funtion that will be invoked when a user clicks a button',
    },
    type: {
      description: 'Specifies what kind of button is this',
    },
    style: {
      description: 'Custom css styles passed as object',
    },
    size: {
      description: 'Determines the size of the button',
    },
    startIcon: {
      description: 'Icon of the button presented on the left side of the content',
    },
    endIcon: {
      description: 'Icon of the button presented on the right side of the content',
    },
    disabled: {
      description:
        'Indicates whether the button is disabled or not and if it is disabled then the button becomes unclickable and its styles alter to simulate unavailability',
    },
  },
} as ComponentMeta<typeof Button>;

const Template: ComponentStory<typeof Button> = (args) => <Button {...args} />;

export const Default = Template.bind({});
Default.args = {
  content: 'Default button',
};

export const WithIcon = Template.bind({});
WithIcon.args = {
  content: <SaveIcon />,
};

export const WithCustomClass = Template.bind({});
WithCustomClass.args = {
  content: 'Custom class',
  className: 'p-10 bg-red-500',
};

export const WithLoading = Template.bind({});
WithLoading.args = {
  content: 'Loading button',
  loading: true,
};

export const WithErrorVariant = Template.bind({});
WithErrorVariant.args = {
  content: 'Error button',
  variant: 'error',
};

export const WithInfoVariant = Template.bind({});
WithInfoVariant.args = {
  content: 'Info button',
  variant: 'info',
};

export const WithWarningVariant = Template.bind({});
WithWarningVariant.args = {
  content: 'Warning button',
  variant: 'warning',
};

export const WithSuccessVariant = Template.bind({});
WithSuccessVariant.args = {
  content: 'Success button',
  variant: 'success',
};

export const WithCustomStyles = Template.bind({});
WithCustomStyles.args = {
  content: 'Custom styles',
  style: { border: '5px solid red' },
};

export const WithSize = Template.bind({});
WithSize.args = {
  content: 'With size',
  size: 'large',
};

export const WithStartIcon = Template.bind({});
WithStartIcon.args = {
  content: 'Start icon',
  startIcon: <SaveIcon />,
};

export const WithEndIcon = Template.bind({});
WithEndIcon.args = {
  content: 'End icon',
  endIcon: <SaveIcon />,
};

export const WithDisabled = Template.bind({});
WithDisabled.args = {
  content: 'Disabled button',
  disabled: true,
};

export const WithDisabledAndLoading = Template.bind({});
WithDisabledAndLoading.args = {
  content: 'Disabled loading button',
  disabled: true,
  loading: true,
};
