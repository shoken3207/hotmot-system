import { convertAdminHistories, createOrderHistoriesResponse } from '../js/convertOrderDetailHistory.js';
import {
  ce,
  gebi,
  ac,
  addClasses,
  removeClass,
  setSrc,
  setHref,
  showToast,
} from '../js/utils.js';
const userIdEl = gebi('userId');
console.log(userIdEl.value)

const getDate = (arg_date) => {
	let now;
	if(arg_date) {
		now = new Date(arg_date);
	}else {
		now = new Date();
	}
	const year = now.getFullYear().toString().padStart(2, '0');
	const month = (now.getMonth() + 1).toString().padStart(2, '0');
	const date = now.getDate().toString().padStart(2, '0');
	return {year, month, date};
}

const fetchOrderDetailHistories = async (fromDate, toDate) => {
	await fetch(`/hotmot/AdminScreenServlet?userId=${userIdEl.value}&fromDate=${fromDate}&toDate=${toDate}`, {
      method: 'GET',
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then((res) => {
		  console.log("res: ", res);
        if (res.message) {
          showToast({ text: res.message });
        }
        const histories = convertAdminHistories(res);
        console.log("histories: ", histories)
      })
      .catch((err) => console.log('err', err));
}

const selectDateEl = gebi("selectDate");
console.log(selectDateEl)
 window.addEventListener("DOMContentLoaded", async () => {
	 const {year, month, date} = getDate();
	 selectDateEl.value=`${year}-${month}-${date}`;
	 selectDateEl.addEventListener("change", async (e) => {
		 console.log(e.target.value);
		 const {year, month, date} = getDate(e.target.value);
		 const fromDate = `${year}-${month}-${date} 00:00:00`;
		 const toDate = `${year}-${month}-${date} 23:59:59`;
		 fetchOrderDetailHistories(fromDate, toDate)
	 })
	 const fromDate = `${year}-${month}-${date} 00:00:00`;
	 const toDate = `${year}-${month}-${date} 23:59:59`;
	 
	 await fetchOrderDetailHistories(fromDate, toDate);
 })