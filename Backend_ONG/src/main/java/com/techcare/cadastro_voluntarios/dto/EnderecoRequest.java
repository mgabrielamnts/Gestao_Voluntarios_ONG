    package com.techcare.cadastro_voluntarios.dto;

    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Size;

    public class EnderecoRequest {

        @NotBlank
        @Size(min = 8, max = 8, message = "CEP deve ter 8 dígitos")
        private String cep;

        @NotBlank
        private String logradouro;

        @NotBlank
        private String numero;

        private String complemento;

        @NotBlank
        private String bairro;

        @NotBlank
        private String cidade;

        @NotBlank
        @Size(min = 2, max = 2, message = "Estado deve ser a sigla UF com 2 letras")
        private String estado;

        public String getCep() { return cep; }
        public void setCep(String cep) { this.cep = cep; }

        public String getLogradouro() { return logradouro; }
        public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

        public String getNumero() { return numero; }
        public void setNumero(String numero) { this.numero = numero; }

        public String getComplemento() { return complemento; }
        public void setComplemento(String complemento) { this.complemento = complemento; }

        public String getBairro() { return bairro; }
        public void setBairro(String bairro) { this.bairro = bairro; }

        public String getCidade() { return cidade; }
        public void setCidade(String cidade) { this.cidade = cidade; }

        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }
    }