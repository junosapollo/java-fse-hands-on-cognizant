import { render, screen, waitFor } from '@testing-library/react';
import App from './App';

test('loads and renders posts', async () => {
  global.fetch = jest.fn().mockResolvedValue({
    ok: true,
    json: async () => [{ userId: 1, id: 1, title: 'A post', body: 'Body' }]
  });
  render(<App />);
  expect(screen.getByRole('heading', { name: 'Posts' })).toBeTruthy();
  await waitFor(() => expect(screen.getByText('A post')).toBeTruthy());
});
