/**
 * 
 */

 export const PRODUCT_CATEGORIES = {
  LIMITED_TIME: 1,
  BENTO: 2,
  SIDE_DISH_ONLY: 3,
  SIDE_MENU: 4,
  ALL: 5,
};

export const TABS = [
  { id: PRODUCT_CATEGORIES.ALL, name: '全て' },
  { id: PRODUCT_CATEGORIES.LIMITED_TIME, name: '  期間限定商品' },
  { id: PRODUCT_CATEGORIES.BENTO, name: '弁当' },
  { id: PRODUCT_CATEGORIES.SIDE_DISH_ONLY, name: 'おかずのみ' },
  { id: PRODUCT_CATEGORIES.SIDE_MENU, name: 'サイドメニュー' },
];