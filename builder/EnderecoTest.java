package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class EnderecoTest {

    @Test
    public void testBuilder() {
        Endereco endereco = Endereco.builder()
                .rua("Rua A")
                .setor("Setor B")
                .cep("12345-678")
                .numero("123")
                .cidade("Cidade X")
                .estado("Estado Y")
                .bloco("Bloco Z")
                .quadra("Quadra W")
                .build();

        assertEquals("Rua A", endereco.getRua());
        assertEquals("Setor B", endereco.getSetor());
        assertEquals("12345-678", endereco.getCep());
        assertEquals("123", endereco.getNumero());
        assertEquals("Cidade X", endereco.getCidade());
        assertEquals("Estado Y", endereco.getEstado());
        assertEquals("Bloco Z", endereco.getBloco());
        assertEquals("Quadra W", endereco.getQuadra());
    }
}
