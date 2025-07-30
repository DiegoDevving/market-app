package com.tecdesoftware.market.web.controller;

import com.tecdesoftware.market.domain.Purchase;
import com.tecdesoftware.market.domain.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchase Controller", description = "Buy products in the store")

public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    @Operation(
            summary = "Get all purchases",
            description = "Return a list of all available purchases"
    )
    @ApiResponse(responseCode = "200", description = "Successful retrieval of purchases")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{idClient}")
    @Operation(

            summary = "Get purchases by client ID",
            description = "Return a purchase by its client ID if it exists"
    )
    @ApiResponse( responseCode = "200", description = "Purchase found")
    @ApiResponse ( responseCode = "404", description = "Purchase not found")
    @ApiResponse ( responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Purchase>> getByClient(
            @Parameter(description = "Client ID of the purchase to be retrieved",
                    example = "7", required = true)
            @PathVariable("idClient") String clientId){
        return purchaseService.getByClient(clientId).map(
                purchases -> new ResponseEntity<>(purchases, HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }

}
