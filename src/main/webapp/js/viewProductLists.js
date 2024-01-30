import {fetchProductsByCategory} from '../js/master.js'
import { PRODUCT_CATEGORIES, TABS } from './const.js'
 
 const lists = document.getElementById("lists");
 const tabs = document.getElementById("tabs");
 
 
 
 console.log("list: ", lists)
 
 window.addEventListener("DOMContentLoaded", async () => {
	 let selectTab = 5;
	 TABS.forEach(({id, name}) => {
		 const tab = document.createElement("div");
		 tab.classList.add("tab");
		 if(id === selectTab) {
			 tab.classList.add("active");
		 }
		 tab.innerText = name;
		 tab.setAttribute("value", id);
		 tab.addEventListener("click", async () => {
			 selectTab = id;
			 const tabItems = document.querySelectorAll('.tab');
			 tabItems.forEach((tabItem) => {
			    tabItem.classList.remove('active');
			  })
			 tab.classList.add("active");
			 while( lists.firstChild ){
			  lists.removeChild( lists.firstChild );
			}
			 const data = await fetchProductsByCategory(selectTab);
			 data.map(x => {
				 const listItem = document.createElement("div");
				 listItem.classList.add("list-item")
				 createImgEl({src: x.image, parentEl: listItem, className: "image"});
				 const divEl = document.createElement("div");
				 divEl.classList.add("text-group");
				 createH3El({text: x.name, parentEl: divEl, className: "text"});
				 createH3El({text: `${x.price}円`, parentEl: divEl, className: "text"});
				 listItem.appendChild(divEl);
				 if(x.rices.length > 0) {
				 	createSelecRicetEl({options: x.rices, parentEl: listItem, className: "select"});	 
				 }
				 const btnEl = document.createElement("button");
				 btnEl.innerText = "button";
				 btnEl.addEventListener("click", async () => {
					 console.log("click");
					 await func1()
				 })
				 listItem.appendChild(btnEl)
				 lists.appendChild(listItem);
			 })
			 
		 })
		 tabs.appendChild(tab);
	 })
	 const data = await fetchProductsByCategory(5);
	 data.map(x => {
		 const listItem = document.createElement("div");
		 listItem.classList.add("list-item")
		 createImgEl({src: x.image, parentEl: listItem, className: "image"});
		 const divEl = document.createElement("div");
		 divEl.classList.add("text-group");
		 createH3El({text: x.name, parentEl: divEl, className: "text"});
		 createH3El({text: `${x.price}円`, parentEl: divEl, className: "text"});
		 listItem.appendChild(divEl);
		 if(x.rices.length > 0) {
		 	createSelecRicetEl({options: x.rices, parentEl: listItem, className: "select"});	 
		 }
		 const btnEl = document.createElement("button");
		 btnEl.innerText = "button";
		 btnEl.addEventListener("click", async () => {
			 console.log("click");
			 await func1()
		 })
		 listItem.appendChild(btnEl)
		 lists.appendChild(listItem);
	 })
 })
 
 const func1 = async () => {
	 await fetch('/hotmot/CartServlet', {method: 'POST', body: {}}) 
//        .then(response => {
//			console.log("res: ", response)
//            if (!response.ok) {
//                throw new Error('Network response was not ok');
//            }
//            return response.json(); // JSONデータを取得して解析する
//        })
//        .then(data => {
//            // 取得したデータを使って何らかの処理を行う
//            console.log('Data from Servlet:', data);
//            // ここで取得したデータを適切に処理する（例えば、HTMLに表示するなど）
//        })
//        .catch(error => {
//            console.error('There was a problem with the fetch operation:', error);
//        });
 }
 
 
 const createPEl = ({text, className, parentEl}) => {
	 const pEl = document.createElement("p");
	 pEl.innerText = text;
	 if(className) {
		 pEl.classList.add(className);
	 }
	 parentEl.appendChild(pEl);
 }
 
  const createH3El = ({text, className, parentEl}) => {
	 const pEl = document.createElement("h3");
	 pEl.innerText = text;
	 if(className) {
		 pEl.classList.add(className);
	 }
	 parentEl.appendChild(pEl);
 }
 
  const createImgEl = ({src, alt, className, parentEl}) => {
	 const imgEl = document.createElement("img");
	 imgEl.setAttribute("src", src);
	 if(alt) {
		 imgEl.setAttribute("alt", alt);
	 }
	 if(className) {
		 imgEl.classList.add(className);
	 }
	 parentEl.appendChild(imgEl);
 }
 
 const createSelecRicetEl = ({options, className, parentEl}) => {
	 const selectEl = document.createElement("select");
	 selectEl.classList.add(className);
	 options.forEach(({id, name, price}) => {
		 const optionEl = document.createElement("option");
		 optionEl.innerText = `${name} (${price}円)`;
		 optionEl.setAttribute("value", id);
		 selectEl.appendChild(optionEl);
	 })
	 if(className) {
		 selectEl.classList.add(className);
	 }
	 parentEl.appendChild(selectEl);
 }
 
 
 const createCounterEl = () => {
	 
 }