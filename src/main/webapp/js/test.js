import rice from '../jsons/Rice.json' assert { type: 'json' };
import product from '../jsons/Product.json' assert { type: 'json' };
import ProductData from '../jsons/Product.json' assert { type: 'json' };
import { fetchProductsByCategory } from '../js/master.js';
import { gebi } from '../js/utils.js';

window.addEventListener('DOMContentLoaded', async () => {
  const buttonEl = gebi('button');
  buttonEl.addEventListener('click', async () => {
    await fetch('/hotmot/ResponseTestServlet', {
      method: 'POST',
      body: JSON.stringify({}),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then((data) => {})
      .catch((error) => {});
  });
});
