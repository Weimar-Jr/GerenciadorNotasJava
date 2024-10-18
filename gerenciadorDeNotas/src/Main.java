import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Nota
{
    private String titulo;
    private String data;
    private String nota;

    //get
    public String GetTitulo()
    {
        return titulo;
    }

    public String GetNota()
    {
        return nota;
    }
    //set

    public void SetTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public void SetData(String data)
    {
        this.data = data;
    }

    public void SetNota(String nota)
    {
        this.nota = nota;
    }

    public void MostrarNotaCompleta()
    {
        System.out.printf("Titulo: %s \nData ou ultima alteração: %s\nNota: %s", titulo, data, nota);
    }

    public Nota (String titulo, String nota, String data)
    {
        this.titulo = titulo;
        this.nota = nota;
        this.data = data;
    }
}

class CriarNotas
{
    public static Scanner scannear = new Scanner(System.in);
    static void CriarNota()
    {
        System.out.println("Digite o titulo da nova nota.");
        String respostaTitulo = scannear.nextLine();
        String verificacao = Verificacao.Verificar(respostaTitulo);
        if(verificacao.equals("verificado"))
        {
            System.out.println("Agora digite a nota: ");
            String respostaNota = scannear.nextLine();
            String verificacao2 = Verificacao.Verificar(respostaNota);
            if(verificacao2.equals("verificado"))
            {
                LocalDate data = LocalDate.now();
                DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataFormatada = data.format(formatoData);
                Nota nota = new Nota(respostaTitulo, respostaNota, dataFormatada);
                Main.notas.add(nota);
            }
        }else{
            CriarNota();
        }

    }
}

class Verificacao
{
    public static String resultado;
    public static Scanner scannear = new Scanner(System.in);
    static String Verificar(String texto)
    {

        System.out.printf("Sua resposta: %s", texto);
        System.out.println("A sua resposta esta correta? s/n");
        String resposta = scannear.nextLine();
        if( resposta.equals("s") || resposta.equals("S"))
        {
            return resultado = "verificado";
        }
        return resultado;
    }
}

class EditarNotas
{
    public static Scanner scannear = new Scanner(System.in);
    static void EditarNota()
    {
        Nota notaEncontrada = null;

        System.out.println("Digite o titulo da nota que você deseja editar: ");
        String resposta = scannear.nextLine();

        for( Nota n : Main.notas)
        {
            if(n.GetTitulo().equals(resposta))
            {
                notaEncontrada = n;
            }
        }

        if(notaEncontrada != null)
        {
            System.out.println("Digite \"1\" para editar o titulo.");
            System.out.println("Digite \"2\" para editar a nota.");
            System.out.println("Digite \"3\" para sair.");
            int resposta2 = scannear.nextInt();

            switch (resposta2)
            {
                case 1:
                    System.out.printf("Titulo atual: %s \n", notaEncontrada.GetTitulo());
                    System.out.println("Digite o novo titulo: ");
                    String resposta3 = scannear.nextLine();
                    String verificado = Verificacao.Verificar(resposta3);
                    if(verificado.equals("verificado") )
                    {
                        notaEncontrada.SetTitulo(resposta3);
                        LocalDate data = LocalDate.now();
                        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        String dataFormatada = data.format(formatoData);
                        notaEncontrada.SetData(dataFormatada);

                        break;
                    }else
                    {
                        EditarNota();
                    }
                    break;
                case 2:
                    System.out.printf("Nota atual: %s \n", notaEncontrada.GetNota());
                    System.out.println("Digite a nova nota:");
                    String resposta4 = scannear.nextLine();
                    String verificado2 = Verificacao.Verificar(resposta4);
                    if(verificado2.equals("verificado"))
                    {
                        notaEncontrada.SetNota(resposta4);
                        LocalDate data = LocalDate.now();
                        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        String dataFormatada = data.format(formatoData);
                        notaEncontrada.SetData(dataFormatada);
                        break;
                    }else
                    {
                        EditarNota();
                    }
                    break;
                case 3:
                    System.out.println("Você saiu com sucesso.");
                    break;
                default:
                    System.out.println("Opção invalida, digite só os numeros exibidos.");
                    break;
            }

        }
        else
        {
            System.out.println("Nota não encontrada, verifique o titulo e tente de novo.");
        }
    }
}

class MostrarNotas
{
    static void MostrarAsNotas()
    {
        System.out.println("Titulos das notas: ");
        for( Nota n : Main.notas)
        {
            System.out.println(n.GetTitulo());
        }
    }
}

class VerNota
{
    public static Scanner scannear = new Scanner(System.in);
    static void PesquisarNota()
    {
        System.out.println("Digite o titulo da nota que você deseja ver: ");
        String resposta = scannear.nextLine();

        for(Nota n : Main.notas)
        {
            if(n.GetTitulo().equals(resposta) )
            {
                n.MostrarNotaCompleta();
            }
        }
    }
}

public class Main {
    static List<Nota> notas = new ArrayList<>();
    static Scanner scannear = new Scanner(System.in);
    public static void main(String[] args) {
        boolean funcionando = true;
        do {

            try{

                System.out.println("Bem vindo ao gerenciador de notas.");
                System.out.println("Digite \"1\" para adicionar uma nova nota.");
                System.out.println("Digite \"2\" para editar uma nota.");
                System.out.println("Digite \"3\" para mostrar todas as notas.");
                System.out.println("Digite \"4\" para ver uma nota especifica.");
                System.out.println("Digite \"5\" para sair do programa.");

                int resposta = scannear.nextInt();
                switch (resposta) {
                    case 1:
                        CriarNotas.CriarNota();
                        break;
                    case 2:
                        EditarNotas.EditarNota();
                        break;
                    case 3:
                        MostrarNotas.MostrarAsNotas();
                        break;
                    case 4:
                        VerNota.PesquisarNota();
                        break;
                    case 5:
                        System.out.println("Saindo...");
                        funcionando = false;
                        break;
                    default:
                        System.out.println("opção invalida, digite apenas as opções do menu.");
                        break;
                }
            }catch (NumberFormatException e ){
                System.out.println("Resposta em formato invalido, por favor apenas responda com o que o menu pede.");
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

        } while (funcionando);


    }
}