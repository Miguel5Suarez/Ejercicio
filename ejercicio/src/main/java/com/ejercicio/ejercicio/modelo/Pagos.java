package com.ejercicio.ejercicio.modelo;

import java.math.BigDecimal;

import jakarta.annotation.Nonnull;

public class Pagos {
	@Nonnull
	private Long id;
	@Nonnull
	private String concepto;
	@Nonnull
	private int cantidadProductos;

	private String beneficiario;

	private String deudor;
	@Nonnull
	private BigDecimal monto;
	@Nonnull
	private String estatusPago;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * @return the cantidadProductos
	 */
	public int getCantidadProductos() {
		return cantidadProductos;
	}

	/**
	 * @param cantidadProductos the cantidadProductos to set
	 */
	public void setCantidadProductos(int cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}

	/**
	 * @return the beneficiario
	 */
	public String getBeneficiario() {
		return beneficiario;
	}

	/**
	 * @param beneficiario the beneficiario to set
	 */
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	/**
	 * @return the deudor
	 */
	public String getDeudor() {
		return deudor;
	}

	/**
	 * @param deudor the deudor to set
	 */
	public void setDeudor(String deudor) {
		this.deudor = deudor;
	}

	/**
	 * @return the monto
	 */
	public BigDecimal getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	/**
	 * @return the estatusPago
	 */
	public String getEstatusPago() {
		return estatusPago;
	}

	/**
	 * @param estatusPago the estatusPago to set
	 */
	public void setEstatusPago(String estatusPago) {
		this.estatusPago = estatusPago;
	}
}
