package Project.CoffeVendingMachine;

import Project.CoffeVendingMachine.entity.Drink;
import Project.CoffeVendingMachine.entity.Ingredient;
import Project.CoffeVendingMachine.repository.DrinkRepository;
import Project.CoffeVendingMachine.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DrinkInitializer{

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private IngredientRepository ingredientRepository;


    public void initializeSystem() {

        initializeDrinks();
        initializeIngredients();
    }

    private void initializeDrinks() {
        if (drinkRepository.count() == 0) {
            Drink coffee = new Drink(1, "Coffee");
            drinkRepository.save(coffee);

            Drink tea = new Drink(2, "Tea");
            drinkRepository.save(tea);
        }
    }

    private void initializeIngredients() {
        if (ingredientRepository.count() == 0) {
            Ingredient coffeeBeans = new Ingredient(1, "coffeeBeans", 6);
            ingredientRepository.save(coffeeBeans);

            Ingredient teaLeaves = new Ingredient(2, "teaLeaves", 6);
            ingredientRepository.save(teaLeaves);

            Ingredient milk = new Ingredient(3, "milk", 6);
            ingredientRepository.save(milk);

            Ingredient sugar = new Ingredient(4, "sugar", 6);
            ingredientRepository.save(sugar);
        }
    }
}
