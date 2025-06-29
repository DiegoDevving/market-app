package com.tecdesoftware.market.persistence.entity;

import com.tecdesoftware.market.persistence.entity.Producto;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="compras_productos")
public class CompraProducto {

    @EmbeddedId //Sale de la otra clase
    private CompraProductoPK id;

    private Integer cantidad;
    private Double total;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name= "id_compra", insertable=false, updatable=false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name= "id_producto", insertable=false, updatable=false)
    private Producto producto;

    public CompraProductoPK getId() {
        return id;
    }

    public void setId(CompraProductoPK id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
