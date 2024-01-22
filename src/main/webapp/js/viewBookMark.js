/**
 * 
 */

import { gebi } from "../js/utils.js";

const messageEl = gebi("message");

 window.addEventListener("DOMContentLoaded", () => {
	 const message = messageEl.value;
	 console.log("message: ", message, messageEl)
	 if(message) {
		 console.log(message)
		 Toastify({
  text: message,
  duration: 3000,
  destination: "https://github.com/apvarun/toastify-js",
  newWindow: true,
  close: true,
  gravity: "bottom", 
  position: "right", 
  stopOnFocus: true, 
  style: {
    background: "linear-gradient(to right, #00b09b, #96c93d)",
  },
  onClick: function(){} 
}).showToast();
	 }
	 

 })