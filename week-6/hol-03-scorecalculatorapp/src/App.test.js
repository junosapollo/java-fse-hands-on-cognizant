import { render, screen } from '@testing-library/react';
import App from './App';

test('calculates and displays the average score', () => {
  render(<App />);
  expect(screen.getByText(/average score: 94.67/i)).toBeTruthy();
});
