package com.tecdesoftware.market.persistence;

import com.tecdesoftware.market.persistence.crud.ProductoCrudRespository;
import com.tecdesoftware.market.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

//Le dice a Spring que este repositorio se conecta con la BD
@Repository
public class ProductoRepository {

        private ProductoCrudRespository productoCrudRespository;

        //Me va a dar todos los productos de mi BD
        public List<Producto> getAll() {
            //Convirtiendo un Iterable <T> a una lista de productos List <Producto>
            return (List<Producto>)productoCrudRespository.findAll();
        }

        //Obtener los productos por categoria
        public List<Producto> getByCategoria(int idCategoria) {
            return productoCrudRespository.findByIdCategoriaOrderByNombreAsc(idCategoria);
        }

        //Obtener
        public Optional<List<Producto>> getEscasos (int cantidad) {
            return productoCrudRespository.findByCantidadStockLessThanAndEstado(cantidad, true);
        }

        //Obtener un producto dado el id
        public Optional <Producto> getProductoById(int idProducto) {
            return productoCrudRespository.findById(idProducto);
        }

        //Guardar un producto
        public Producto save(Producto producto) {
            return productoCrudRespository.save(producto);
        }

        //Borrar un producto
        public void delete(Producto producto) {
            productoCrudRespository.delete(producto);
        }
    }

