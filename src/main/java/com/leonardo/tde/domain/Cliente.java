package com.leonardo.tde.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import com.leonardo.tde.enumerable.TipoCliente;

// Cliente - Tabela CLIENTE
@Entity(name = "Cliente")
@Table(name = "CLIENTE")
public class Cliente {

    // Atributos
    // ID - Chave Primária - Serial - Long - Não Nulo - Único
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE_PK", nullable = false, unique = true)
    private Long id;

    // Nome - String - 50 Caracteres - Não Nulo - Único
    @Column(name = "NOME", length = 50, nullable = false, unique = true)
    @NotEmpty(message = "O Nome do Cliente é Obrigatório!")
    @Size(max = 50, message = "O Nome do Cliente Deve Conter no Máximo 50 Caracteres!")
    private String nome;

    // E-Mail - String - 50 Caracteres - Único
    @Column(name = "EMAIL", length = 50, unique = true)
    @Email(message = "E-Mail do Cliente Inválido!")
    @Size(max = 50, message = "O E-Mail do Cliente Deve Conter no Máximo 50 Caracteres!")
    private String email;

    // CPF ou CNPJ - String - 14 Caracteres - Não Nulo - Único
    @Column(name = "CPF_OU_CNPJ", length = 14, nullable = false, unique = true)
    @Size.List(value = {
            @Size(min = 11, max = 11, message = "O CPF do Cliente Deve Ter Apenas 11 Digitos!"),
            @Size(min = 14, max = 14, message = "O CNPJ do Cliente Deve Ter Apenas 14 Digitos!") })
    @NotEmpty(message = "O CPF ou CNPJ do Cliente é Obrigatório!")
    @CPF(message = "CPF do Cliente Inválido!")
    @CNPJ(message = "CNPJ do Cliente Inválido!")
    private String cpfOuCnpj;

    // Tipo de Cliente - TipoCliente - Enum - Não Nulo
    @Column(name = "TIPO_CLIENTE", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "O Tipo do Cliente é Obrigatório!")
    private TipoCliente tipoCliente;

    // Relacionamentos
    // Endereços - Chave Estrangeira - Endereco - Não Nulo
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "cliente")
    private List<Endereco> enderecos;

    // Telefones - Chave Estrangeira - Telefone - Não Nulo
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "cliente")
    private List<Telefone> telefones;

    // Pedidos - Chave Estrangeira - Pedido
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Pedido> pedidos;

    // Getters e Setters
    // ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // E-Mail
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // CPF ou CNPJ
    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    // Tipo de Cliente
    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    // Endereços
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    // Telefones
    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    // Pedidos
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    // To String
    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + ", cpfOuCnpj=" + cpfOuCnpj
                + ", tipoCliente=" + tipoCliente + ", enderecos=" + enderecos + ", telefones=" + telefones
                + ", pedidos=" + pedidos + "]";
    }
}