import { render, screen } from '@testing-library/react';
import App from './App';

test('renders the first React welcome heading', () => {
  render(<App />);
  expect(screen.getByRole('heading', { name: /welcome to the first session of react/i })).toBeTruthy();
});
