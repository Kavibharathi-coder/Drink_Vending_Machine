package Project.CoffeVendingMachine.repository;

import Project.CoffeVendingMachine.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Integer>
{

}
