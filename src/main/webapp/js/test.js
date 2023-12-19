import rice from '../jsons/Rice.json' assert {type: 'json'}
import product from '../jsons/Product.json' assert {type: 'json'}
import ProductData from '../jsons/Product.json' assert {type: 'json'}
import {fetchProductsByCategory} from '../js/master.js'

window.addEventListener("DOMContentLoaded", async () => {
	console.log("called")
	await fetchProductsByCategory(5)
	const options = [
		{
			cartId: 1,
			productId: 3,
			riceId: 4,
			quantity: 2
		},
		{
			cartId: 10,
			productId: 32,
			riceId: 2,
			quantity: 20
		},
		{
			cartId: 2,
			productId: 9,
			riceId: 2,
			quantity: 7
		}
	]
	await fetch('/hotmot/test', {
		method: "POST",
		body: JSON.stringify(options)
	})
        .then(response => {
			console.log("res: ", response)
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Data from Servlet:', data);
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
})
