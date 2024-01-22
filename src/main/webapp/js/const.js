

 export const PRODUCT_CATEGORIES = {
  LIMITED_TIME: 1,
  BENTO: 2,
  SIDE_DISH_ONLY: 3,
  SIDE_MENU: 4,
  ALL: 5,
};

export const TABS = [
  { id: PRODUCT_CATEGORIES.ALL, name: '全て' },
  { id: PRODUCT_CATEGORIES.LIMITED_TIME, name: '  期間限定' },
  { id: PRODUCT_CATEGORIES.BENTO, name: '弁当' },
  { id: PRODUCT_CATEGORIES.SIDE_DISH_ONLY, name: 'おかずのみ' },
  { id: PRODUCT_CATEGORIES.SIDE_MENU, name: 'サイド' },
];

export const RICE_TYPE = {
	MEDIUM: 1,
	LARGE: 2,
	SMALL: 3,
	MEDIUM_STICKY_BARLEY: 4,
  	LARGE_STICKY_BARLEY: 5,
  	SMALL_STICKY_BARLEY: 6,
  	NONE: 7,
}

export const HEADER_INFO = [
	{
		label: "ホーム",
		servletName: "ProductListServlet",
		iconClass: "fa-house"
	},{
		label: "ブックマーク",
		servletName: "BookMarkServlet",
		iconClass: "fa-star"
	},{
		label: "カート",
		servletName: "CartDetailListServlet",
		iconClass: "fa-cart-shopping"
	},{
		label: "注文履歴",
		servletName: "ProductListServlet",
		iconClass: "fa-clock-rotate-left"
	},
]

export const SAMPLE_DATA = [
  {
    id: 1,
    price: 590,
    productId: 1,
    productImage:
      "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
    productName: "梅おろし豚しゃぶ弁当",
    quantity: 3,
    riceName: "ライス: 小盛",
  },
  {
    id: 2,
    price: 590,
    productId: 1,
    productImage:
      "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
    productName: "梅おろし豚しゃぶ弁当",
    quantity: 3,
    riceName: "ライス: 小盛",
  },
  {
    id: 3,
    price: 590,
    productId: 1,
    productImage:
      "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
    productName: "梅おろし豚しゃぶ弁当",
    quantity: 3,
    riceName: "ライス: 小盛",
  },
  {
    id: 4,
    price: 590,
    productId: 1,
    productImage:
      "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
    productName: "梅おろし豚しゃぶ弁当",
    quantity: 3,
    riceName: "ライス: 小盛",
  },
  {
    id: 5,
    price: 590,
    productId: 1,
    productImage:
      "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
    productName: "梅おろし豚しゃぶ弁当",
    quantity: 3,
    riceName: "ライス: 小盛",
  },
  {
    id: 6,
    price: 590,
    productId: 1,
    productImage:
      "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg",
    productName: "梅おろし豚しゃぶ弁当",
    quantity: 3,
    riceName: "ライス: 小盛",
  },
];