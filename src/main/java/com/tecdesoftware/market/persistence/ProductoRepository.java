package com.tecdesoftware.market.persistence;

import com.tecdesoftware.market.domain.Product;
import com.tecdesoftware.market.domain.repository.ProductRepository;
import com.tecdesoftware.market.persistence.crud.ProductoCrudRespository;
import com.tecdesoftware.market.persistence.entity.Producto;
import com.tecdesoftware.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Le dice a Spring que este repositorio se conecta con la BD
@Repository
public class ProductoRepository implements ProductRepository {

    //Inyectado automaticamente: Sping crea el objeto por ti
    @Autowired
    private ProductoCrudRespository productoCrudRespository;

    @Autowired
    private ProductMapper productMapper;

    //Me va a dar todos los productos de mi BD
    public List<Product> getAll() {

        //Convirtiendo un Iterable <T> a una lista de productos List <Producto>
        List<Producto>productos = (List<Producto>)productoCrudRespository.findAll();
        return productMapper.toProducts(productos);
    }

    //Obtener los productos por categoria
    public Optional <List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRespository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }

    //Obtener productos que se vayan a agotar
    public Optional<List<Product>> getScarsProducts (int quantity) {
        Optional <List<Producto>> productos = productoCrudRespository.findByCantidadStockLessThanAndEstado(quantity, true);
        //No hay un mapper que convierta una lista de opcionales se tiene que usar map
        return productos.map(prods -> productMapper.toProducts(prods));
    }

    //Obtener un producto dado el id
    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRespository.findById(productId).map(producto -> productMapper.toProduct(producto));
    }

    //Guardar un producto
    @Override
    public Product save(Product product) {
        Producto producto = productMapper.toProducto(product);
        return productMapper.toProduct(productoCrudRespository.save(producto));
    }

    //Borrar un producto
    @Override
    public void delete(int productId) {
        productoCrudRespository.deleteById(productId);
    }

}

