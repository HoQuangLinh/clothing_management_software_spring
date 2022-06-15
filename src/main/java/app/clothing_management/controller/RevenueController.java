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
            System.out.println(objectNode);
            String from = objectNode.get("fromDate").asText();
            String to = objectNode.get("toDate").asText();

            System.out.println(from);
            System.out.println(to);
            from = formatStringDate(from);
            to = formatStringDate(to);

            SimpleDateFormat format = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");

            Date _from = format.parse(from);
            Date _to = format.parse(to);
            return revenueService.getProductsSellByDate(_from, _to);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public String formatStringDate(String date){
        String[] parts = date.split(" ");
        String result = parts[0] + ", " + parts[1] + " " + parts[2] + " " + parts[3] + " " + parts[4];
        return result;
    }
}
