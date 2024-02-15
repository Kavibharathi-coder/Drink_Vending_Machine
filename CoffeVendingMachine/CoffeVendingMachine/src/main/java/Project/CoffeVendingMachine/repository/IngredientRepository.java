package Project.CoffeVendingMachine.repository;

import Project.CoffeVendingMachine.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface IngredientRepository extends JpaRepository<Ingredient, Integer>
{
    Ingredient findByName(String name);
}
