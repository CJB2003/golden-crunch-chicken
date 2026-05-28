import type {
    Toppings,
    Sauce,
    ChickenCut,
    PrepStyle,
    SauceType,
    SideType,
    DrinkSize,
    } from "../types";

// Chicken cuts
export const CHICKEN_CUTS: { value: ChickenCut; label: string; price: number }[] = [
  { value: "WINGS", label: "Wings", price: 8.99 },
  { value: "DRUMSTICKS", label: "Drumsticks", price: 9.99 },
  { value: "BONELESS", label: "Boneless", price: 10.50 },
  { value: "WHOLE_CHICKEN", label: "Whole Chicken", price: 20.00 },
];

// Prep styles
export const PREP_STYLES: { value: PrepStyle; label: string }[] = [
  { value: "ORIGINAL_CRISPY", label: "Original Crispy" },
  { value: "EXTRA_CRISPY", label: "Extra Crispy" },
  { value: "GRILLED", label: "Grilled" },
];

// Premium toppings carry different upcharges by cut
export const PREMIUM_TOPPINGS: Toppings[] = [
  {
    toppingName: "Bonito Flakes",
    toppingType: "PREMIUM",
    priceWings: 0.75,
    priceDrumsticks: 0.99,
    priceBoneless: 1.50,
    priceWhole: 2.50,
  },
  {
    toppingName: "Cheese Powder",
    toppingType: "PREMIUM",
    priceWings: 0.75,
    priceDrumsticks: 0.99,
    priceBoneless: 1.50,
    priceWhole: 2.50,
  },
  {
    toppingName: "Corn Cheese",
    toppingType: "PREMIUM",
    priceWings: 0.75,
    priceDrumsticks: 0.99,
    priceBoneless: 1.50,
    priceWhole: 2.50,
  },
  {
    toppingName: "Garlic Butter",
    toppingType: "PREMIUM",
    priceWings: 0.75,
    priceDrumsticks: 0.99,
    priceBoneless: 1.50,
    priceWhole: 2.50,
  },
];

// Regular toppings are free
export const REGULAR_TOPPINGS: Toppings[] = [
  {
    toppingName: "Scallions",
    toppingType: "REGULAR",
    priceWings: 0,
    priceDrumsticks: 0,
    priceBoneless: 0,
    priceWhole: 0,
  },
  {
    toppingName: "Sesame Seeds",
    toppingType: "REGULAR",
    priceWings: 0,
    priceDrumsticks: 0,
    priceBoneless: 0,
    priceWhole: 0,
  },
  {
    toppingName: "Jalapeños",
    toppingType: "REGULAR",
    priceWings: 0,
    priceDrumsticks: 0,
    priceBoneless: 0,
    priceWhole: 0,
  },
];

// GOLDEN GLAZE
export const GOLDEN_GLAZE: Toppings = {
  toppingName: "Golden Glaze ✨",
  toppingType: "PREMIUM",
  priceWings: 0.50,
  priceDrumsticks: 0.50,
  priceBoneless: 0.50,
  priceWhole: 0.50,
};

// SAUCES
export const SAUCES: { value: SauceType; label: string }[] = [
  { value: "SOY_GARLIC", label: "Soy Garlic" },
  { value: "GANG_JEONG", label: "Gangjeong" },
  { value: "GALBI", label: "Galbi" },
  { value: "HONEY_BUTTER", label: "Honey Butter" },
  { value: "KOREAN_BBQ", label: "Korean BBQ" },
  { value: "BULGOGI", label: "Bulgogi" },
  { value: "HOT_SPICY", label: "Hot Spicy" },
];

// DRINKS
export const DRINK_FLAVORS: string[] = [
  "Boba Tea",
  "Iced Tea",
  "Banana Milk",
  "Coke",
  "Sprite",
  "Milkis",
  "Dr. Pepper",
  "Lemonade",
  "Water",
];

export const DRINK_SIZES: { value: DrinkSize; label: string; price: number }[] = [
  { value: "SMALL", label: "Small", price: 2.00 },
  { value: "MEDIUM", label: "Medium", price: 2.50 },
  { value: "LARGE", label: "Large", price: 3.00 },
];

// ==================== SIDES ====================
export const SIDES_MENU: { value: SideType; label: string; price: number }[] = [
  { value: "TTEOKBOKKI", label: "Tteokbokki", price: 5.00 },
  { value: "CHEESE_BALLS", label: "Cheese Balls", price: 4.00 },
  { value: "FRIES", label: "Fries", price: 2.50 },
  { value: "PICKLED_RADISH", label: "Pickled Radish", price: 1.50 },
  { value: "KIMCHI", label: "Kimchi", price: 0.50 },
];

// Topping and sauce limits
export const MAX_TOPPINGS_PER_CHICKEN = 4;
export const MAX_SAUCES_PER_CHICKEN = 2;