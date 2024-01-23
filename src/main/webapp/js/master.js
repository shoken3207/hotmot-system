//import { PRODUCT_CATEGORIES } from '../const';
import AllergyData from '../jsons/Allergy.json' assert {type: 'json'};
import ProductData from '../jsons/Product.json' assert {type: 'json'};
import ProductAllergyData from '../jsons/ProductAllergy.json' assert {type: 'json'};
import RiceData from '../jsons/Rice.json' assert {type: 'json'};
import RiceGroupDetailData from '../jsons/RiceGroupDetail.json' assert {type: 'json'};

// ライス変換
const _fetchConvertRices = (riceGroupId) => {
  const riceGroupDetailData = RiceGroupDetailData;
  const riceData = RiceData;
  const currentRiceGroupDetails = riceGroupDetailData.filter(
    (x) => x.riceGroupId === riceGroupId
  );
  const currentRices = currentRiceGroupDetails.map((x) => {
    const rice = riceData.find((y) => y.id === x.riceId);
    return rice;
  });
  const filterRices = currentRices.filter((x) => {
    return x !== undefined;
  })
  return filterRices;
};

// Allergy変換
const _fetchConvertAllergys = (productId) => {
  const allergyData = AllergyData;
  const productAllergyData = ProductAllergyData;

  const currentProductAllergys = productAllergyData.filter(
    (x) => x.productId === productId
  );

  const currentAllergys = currentProductAllergys.map((x) => {
    const allergy = allergyData.find((y) => y.id === x.allergyId);
    return allergy;
  });

  const filterAllergys = currentAllergys.filter(
    (x) => x !== undefined
  )

  return filterAllergys;
};

// 商品リスト変換
const _convertListProducts = (products) => {
  const convertProducts = products.map((product) => {
    const rices = _fetchConvertRices(product.riceGroupId);
    const { id, name, price, listImage, productCategoryId } = product;
    return {
      id,
      name,
      price,
      categoryId: productCategoryId,
      image: listImage,
      rices,
    };
  });
  return convertProducts;
};

const fetchProductsByCategory = (category) => {
	console.log("bbb")
  const productData = ProductData;
  const filterProducts =
    category === 5
      ? productData
      : productData.filter((x) => x.productCategoryId === category);
      
  const convertProducts = _convertListProducts(filterProducts);
  	console.log("aa: ", convertProducts)
  return convertProducts;
};


// 商品詳細変換
const _convertDetailProduct = (product) => {
  const rices = _fetchConvertRices(product.riceGroupId);
  const allergys = _fetchConvertAllergys(product.id);
  const { id, name, price, detailImage, desc } = product;
  return {
    id,
    name,
    price,
    image: detailImage,
    desc,
    rices,
    allergys,
  };
};

const fetchDetailProduct = (productId) => {
	console.log("aa: ", productId)
  const productData = ProductData;
  const product = productData.find((x) => x.id === productId);
  if (product) {
    const convertProduct = _convertDetailProduct(product);
    console.log("detail: ", convertProduct)
    return convertProduct;
  }
};




export { fetchProductsByCategory, fetchDetailProduct };