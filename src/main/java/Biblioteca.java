import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Biblioteca {

    private final ArrayList<Livro> livros;
    private final ArrayList<Autor> autores;
    private final ArrayList<Editora> editoras;
    private final ArrayList<LivroEstoque> estoque;

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.testaCadastroVendas();
    }

    public void testaCadastroVendas() {

        String nomeAutor1 = "Escritor";
        String sobrenomeAutor1 = "Escrivão";
        String nomeEditora1 = "Editora Padrão";

        String isbnLivro1 = "111-22-3333-444-5";
        String tituloLivro1 = "Título genérico";
        int paginasLivro1 = 100;
        int exemplaresLivroEstoque1 = 10;
        double valorLivroEstoque1 = 50.00;

        boolean validado = true;

        Autor autor1 = new Autor(nomeAutor1, sobrenomeAutor1);

        if (autor1.nome == null) {
            validado = false;
            System.out.println("Nome do autor1 não pode ser nulo!");
        } else {
            if (autor1.nome == "") {
                validado = false;
                System.out.println("Nome do autor1 não pode ser vazio!");
            }
        }
        if (autor1.sobrenome == null) {
            validado = false;
            System.out.println("Sobrenome do autor1 não pode ser nulo!");
        } else {
            if (autor1.sobrenome == "") {
                validado = false;
                System.out.println("Sobrenome do autor1 não pode ser vazio!");
            }
        }

        if (validado) {
            autores.add(autor1);
        } else {
            validado = true;
        }

        Editora editora1 = new Editora(nomeEditora1);

        if (editora1.nome == null) {
            validado = false;
            System.out.println("Nome da editora1 não pode ser nulo!");
        } else {
            if (editora1.nome == "") {
                validado = false;
                System.out.println("Nome da editora1 não pode ser vazio!");
            }
        }

        if (validado) {
            editoras.add(editora1);
        } else {
            validado = true;
        }

        Livro livro1 = new Livro(autores.get(0), editoras.get(0), isbnLivro1, tituloLivro1, paginasLivro1);

        if (livro1.autor == null) {
            validado = false;
            System.out.println("Autor do livro1 não pode ser nulo!");
        }
        if (livro1.editora == null) {
            validado = false;
            System.out.println("Editora do livro1 não pode ser nula!");
        }
        if (livro1.isbn == null) {
            validado = false;
            System.out.println("ISBN do livro1 não pode ser nulo!");
        } else {
            if (livro1.isbn == "") {
                validado = false;
                System.out.println("ISBN do livro1 não pode ser vazio!");
            }
        }

        if (livro1.titulo == null) {
            validado = false;
            System.out.println("Título do livro1 não pode ser nulo!");
        } else {
            if (livro1.titulo == "") {
                validado = false;
                System.out.println("Título do livro1 não pode ser vazio!");
            }
        }

        if (livro1.paginas <= 0) {
            validado = false;
            System.out.println("Número de páginas deve ser maior que zero!");
        }

        if (validado) {
            livros.add(livro1);
        } else {
            validado = true;
        }

        LivroEstoque livroEstoque1 = new LivroEstoque(livro1, exemplaresLivroEstoque1, valorLivroEstoque1);

        if (livroEstoque1.livro == null) {
            validado = false;
            System.out.println("Livro não pode ser nulo!");
        }

        if (livroEstoque1.exemplares <= 0) {
            validado = false;
            System.out.println("Número de exemplares deve ser maior que zero!");
        }

        if (livroEstoque1.valor <= 0) {
            validado = false;
            System.out.println("Valor do livro deve ser maior que zero!");
        }

        if (validado) {
            estoque.add(livroEstoque1);
            System.out.println("Livro estocado com sucesso!");
        } else {
            validado = true;
        }

        String nomeClienteVenda1 = "Comprador";
        String formaPagamentoVenda1 = "dinheiro";
        double valorDadoVenda1 = 60.00;
        ArrayList<LivroVenda> livrosCompraCliente1 = new ArrayList<>();
        LivroVenda livroVenda1 = new LivroVenda(livroEstoque1, 1);
        livrosCompraCliente1.add(livroVenda1);
        Venda venda1 = new Venda(nomeClienteVenda1, formaPagamentoVenda1, valorDadoVenda1, livrosCompraCliente1);

        if (venda1.nomeCliente == null) {
            validado = false;
            System.out.println("Nome do cliente não pode ser nulo!");
        } else {
            if (venda1.nomeCliente == "") {
                validado = false;
                System.out.println("Nome do cliente não pode ser vazio!");
            }
        }

        if (venda1.livros == null) {
            validado = false;
            System.out.println("Lista de livros da venda não pode ser nula");
        } else {
            if (venda1.livros.isEmpty()) {
                validado = false;
                System.out.println("Lista de livros da venda não pode ser vazia!");
            }
        }

        double valorVenda = 0;

        for (LivroVenda livro: venda1.livros) {
            if (livro.quantidade <= 0) {
                validado = false;
                System.out.println("Quantidades de exemplares do livro " + livro.livroDoEstoque.livro.titulo + "na venda não pode estar zerada!");
            } else {
                if (livro.quantidade > livro.livroDoEstoque.exemplares) {
                    System.out.println("Estoque insuficiente do livro " + livro.livroDoEstoque.livro.titulo + "!");
                    System.out.println("Exemplares disponíveis: " + livro.livroDoEstoque.exemplares);
                } else {
                    valorVenda += livro.livroDoEstoque.valor * livro.quantidade;
                    livro.livroDoEstoque.exemplares -= livro.quantidade;
                }
            }
        }

        switch (venda1.formaPagamento) {
            case "debito" :
                System.out.println("Pagamento no débito");
                break;

            case "credito" :
                System.out.println("Pagamento no credito");
                break;

            case "dinheiro" :
                System.out.println("Pagamento no dinheiro");
                if (venda1.valorDado < valorVenda) {
                    validado = false;
                    System.out.println("Valor insuficiente!");
                } else {
                    System.out.println("Troco: R$" + (venda1.valorDado - valorVenda));
                }
                break;

            default:
                validado = false;
                System.out.println("Forma de pagamento inválida!");
        }

        System.out.println("Venda realizada com sucesso!");

        testeFor();

    }

    public void testeFor() {
        Object[] objects = {null};
        for (Object o : objects) {
            System.out.println(o);
        }
        System.out.println("fim");
    }

    public Biblioteca() {
        this.livros = new ArrayList<Livro>();
        this.autores = new ArrayList<Autor>();
        this.editoras = new ArrayList<Editora>();
        this.estoque = new ArrayList<LivroEstoque>();
    }

}

class Livro {

    public Autor autor;
    public Editora editora;
    public String isbn;
    public String titulo;
    public int paginas;

    public Livro() {

    }

    public Livro(Autor autor, Editora editora, String isbn, String titulo, int paginas) {
        this.autor = autor;
        this.editora = editora;
        this.isbn = isbn;
        this.titulo = titulo;
        this.paginas = paginas;
    }

}

class Autor {

    public String nome;
    public String sobrenome;

    public Autor() {

    }

    public Autor(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }
}

class Editora {

    public String nome;

    public Editora() {

    }

    public Editora(String nome) {
        this.nome = nome;
    }

}

class LivroEstoque {

    public Livro livro;
    public int exemplares;
    public double valor;

    public LivroEstoque() {

    }

    public LivroEstoque(Livro livro, int exemplares, double valor) {
        this.livro = livro;
        this.exemplares = exemplares;
        this.valor = valor;
    }

}

class LivroVenda {

    public LivroEstoque livroDoEstoque;
    public int quantidade;

    public LivroVenda() {

    }

    public LivroVenda(LivroEstoque livroDoEstoque, int quantidade) {
        this.livroDoEstoque = livroDoEstoque;
        this.quantidade = quantidade;
    }

}

class Venda {

    public String nomeCliente;
    public String formaPagamento;
    public  double valorDado;
    public List<LivroVenda> livros;

    public Venda() {

    }

    public Venda(String nomeCliente, String formaPagamento, double valorDado, List<LivroVenda> livros) {
        this.nomeCliente = nomeCliente;
        this.formaPagamento = formaPagamento;
        this.valorDado = valorDado;
        this.livros = livros;
    }
}