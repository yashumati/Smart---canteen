package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@CrossOrigin(origins = "*")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping
    public List<Fooditem> getAllFooditems() {
        return foodRepository.findAll();
    }

    @PostMapping
    public Fooditem addFooditem(@RequestBody Fooditem fooditem) {
        return foodRepository.save(fooditem);
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<Fooditem> updateAvailability(@PathVariable Long id, @RequestParam boolean available) {
        Fooditem item = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        item.setAvailable(available);
        Fooditem updatedItem = foodRepository.save(item);

        return ResponseEntity.ok(updatedItem);
    }
}
