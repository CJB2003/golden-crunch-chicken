import type { Chicken } from "../types";

export interface SignatureCombo {
  id: string;
  name: string;
  description: string;
  fixedPrice: number;
  chicken: Chicken;
}

// Created some signature combos for customer
export const SIGNATURE_COMBOS: SignatureCombo[] = [
  {
    id: "seoul-classic",
    name: "Seoul Classic",
    description:
      "Boneless chicken, original crispy, soy garlic & honey butter, tossed with sesame seeds and scallions",
    fixedPrice: 14.50,
    chicken: {
      chickenCut: "BONELESS",
      prepStyle: "ORIGINAL_CRISPY",
      toppings: [
        {
          toppingName: "Sesame Seeds",
          toppingType: "REGULAR",
          priceWings: 0,
          priceDrumsticks: 0,
          priceBoneless: 0,
          priceWhole: 0,
        },
        {
          toppingName: "Scallions",
          toppingType: "REGULAR",
          priceWings: 0,
          priceDrumsticks: 0,
          priceBoneless: 0,
          priceWhole: 0,
        },
      ],
      sauces: [
        { sauceType: "SOY_GARLIC" },
        { sauceType: "HONEY_BUTTER" },
      ],
      tossedInSauce: true,
    },
  },
  {
    id: "spicy-fire-wings",
    name: "Spicy Fire Wings",
    description:
      "Wings, extra crispy, gangjeong & hot spicy sauce, topped with jalapeños",
    fixedPrice: 11.99,
    chicken: {
      chickenCut: "WINGS",
      prepStyle: "EXTRA_CRISPY",
      toppings: [
        {
          toppingName: "Jalapeños",
          toppingType: "REGULAR",
          priceWings: 0,
          priceDrumsticks: 0,
          priceBoneless: 0,
          priceWhole: 0,
        },
      ],
      sauces: [
        { sauceType: "GANG_JEONG" },
        { sauceType: "HOT_SPICY" },
      ],
      tossedInSauce: true,
    },
  },
  {
    id: "honey-glaze-box",
    name: "Honey Glaze Box",
    description:
      "Whole chicken, original crispy, honey butter & Korean BBQ, golden glaze with scallions",
    fixedPrice: 24.99,
    chicken: {
      chickenCut: "WHOLE_CHICKEN",
      prepStyle: "ORIGINAL_CRISPY",
      toppings: [
        {
          toppingName: "Scallions",
          toppingType: "REGULAR",
          priceWings: 0,
          priceDrumsticks: 0,
          priceBoneless: 0,
          priceWhole: 0,
        },
        {
          toppingName: "Golden Glaze",
          toppingType: "PREMIUM",
          priceWings: 0.50,
          priceDrumsticks: 0.50,
          priceBoneless: 0.50,
          priceWhole: 0.50,
        },
      ],
      sauces: [
        { sauceType: "HONEY_BUTTER" },
        { sauceType: "KOREAN_BBQ" },
      ],
      tossedInSauce: true,
    },
  },
];