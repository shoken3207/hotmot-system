/**
 * 
 */

import { gebi } from "../js/utils.js";
import {createBookMarksResponse} from "../js/convertBookMarks.js";

const messageEl = gebi("message");
const bookMarksEl = gebi("bookMarks");

 window.addEventListener("DOMContentLoaded", () => {
	 const message = messageEl.value;
	 const bookMarks = JSON.parse(bookMarksEl.value);
	 console.log("bookMarks: ", bookMarksEl, bookMarks);
	 const convertBookMarks = createBookMarksResponse(bookMarks);
	 console.log("convertBookMarks: ", convertBookMarks);
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
 
 
