package com.modelot2.models;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_inventario")
public class Inventario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numero")
	private int idInventario;
	
	@Column(name = "fecha_vencimiento", nullable = false)
	private LocalDate fechaVen;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_producto")
	private Producto producto;
	
	@Column(name = "costo_ingreso", nullable = false)
	private double costoIng;
	
	@Column(name = "cantidad", columnDefinition = "INT DEFAULT 1")
	private int cantidad;
	
	@Column(name = "lote", nullable = false)
	private String lote;

	@Column(name = "cod_estado", nullable = false)
	private String codEstado;
	
	
}
