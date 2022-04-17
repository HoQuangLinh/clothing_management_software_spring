package app.clothing_management.controller;

import app.clothing_management.model.OrderDetail;
import app.clothing_management.model.Product;
import app.clothing_management.model.ProductSell;
import app.clothing_management.service.RevenueService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class RevenueController {
    @Autowired
    RevenueService revenueService;

    @GetMapping("/api/revenue/product/sell")
    public List<ProductSell> getProductSell(){
        return revenueService.getProductsSell();
    }
    @PostMapping("/api/revenue/product/sellByDate")
    public List<ProductSell> getProductSellByDate(@RequestBody ObjectNode objectNode){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String from = objectNode.get("fromDate").asText();
            String to = objectNode.get("toDate").asText();
            Date _from = formatter.parse(from);
            Date _to = formatter.parse(to);
            return revenueService.getProductsSellByDate(_from, _to);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
