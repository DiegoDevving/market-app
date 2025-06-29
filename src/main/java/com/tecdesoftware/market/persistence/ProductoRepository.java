package com.tecdesoftware.market.persistence;

import com.tecdesoftware.market.persistence.crud.ProductoCrudRespository;
import com.tecdesoftware.market.persistence.entity.Producto;

import java.util.List;
public class ProductoRepository {

        private ProductoCrudRespository productoCrudRespository;

        //Me va a dar todos los productos de mi BD
        public List<Producto> getAll() {
            //Convirtiendo un Iterable <T> a una lista de productos List <Producto>
            return (List<Producto>)productoCrudRespository.findAll();
        }
    }

