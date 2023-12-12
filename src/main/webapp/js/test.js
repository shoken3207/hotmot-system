import rice from '../jsons/Rice.json' assert {type: 'json'}
import product from '../jsons/Product.json' assert {type: 'json'}
import ProductData from '../jsons/Product.json' assert {type: 'json'}
import {fetchProductsByCategory} from '../js/master.js'

window.addEventListener("DOMContentLoaded", async () => {
	console.log("called")
	await fetchProductsByCategory(5)
	await fetch('/hotmot/test')
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
