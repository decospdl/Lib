package d3c0de.mockup;

import d3c0de.file.File;
import d3c0de.file.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe reponsável pela importação dos serviços de controle da escala.
 *
 * @author André <decospdl@gmail.com>
 */
public class ImportaServico {

    private static final int ESCALA = 0;
    private static final int LINHA = 1;
    private static final int SENTIDO = 2;
    private static final int ORIGEM = 3;
    private static final int DESTINO = 4;
    private static final int INICIO_VIAGEM = 5;
    private static final int FIM_VIAGEM = 6;
    private static final int EVENTO = 7;
    private static final int TIPO = 8;

    private List<List<String>> listaImportado;
    private int falha, sucesso;
    private String erro;
    private Log log;

    public ImportaServico(Log log) {
        this.listaImportado = new ArrayList<>();
        this.log = log;
        falha = 0;
        sucesso = 0;
        selecionaArquivo();
    }

    private void selecionaArquivo() {
        String caminhoArquivo = File.getPathFile();
        listaImportado = File.getImport(',', caminhoArquivo);
    }

    public void validaImportacao() {
        boolean validate;
        log.titleStyle("Importação de Arquivo!\n\n");
        log.normalStyle("Carregando " + listaImportado.size() + " serviços\n");
        for (List lista : listaImportado) {
            erro = "";
            validate = true;
            validate = checkAllValidate(lista, validate);
            log.infoStyle(erro + "\n");
            if (validate == false) {
                falha++;
            } else {
                sucesso++;
            }
        }
        log.sucessStyle("\n" + sucesso + " serviços importados com sucesso!\n");
        log.erroStyle(falha + " serviços que falharam na importação!");
    }

    private boolean checkAllValidate(List lista, boolean validate) {
        validate = validaEscala(lista.get(ESCALA).toString());
        validate = (validate == false) ? false : validaLinha(lista.get(LINHA).toString());
        validate = (validate == false) ? false : validaSentido(lista.get(SENTIDO).toString());
        validate = (validate == false) ? false : validaOrigem(lista.get(ORIGEM).toString());
        validate = (validate == false) ? false : validaDestino(lista.get(DESTINO).toString());
        validate = (validate == false) ? false : validaHorarioViagem(lista.get(INICIO_VIAGEM).toString(), lista.get(FIM_VIAGEM).toString());
        validate = (validate == false) ? false : validaEvento(lista.get(EVENTO).toString());
        validate = (validate == false) ? false : validaTipo(lista.get(TIPO).toString());
        return validate;
    }

    private boolean validaEscala(String escala) {
        if (Integer.parseInt(escala) > 605) {
            log.erroStyle(escala);
            erro += "   [Escala é maior que 605]";
            return false;
        } else {
            log.normalStyle(escala);
            return true;
        }
    }

    private boolean validaLinha(String linha) {
        return true;
    }

    private boolean validaSentido(String sentido) {
        return true;
    }

    private boolean validaOrigem(String origen) {
        return true;
    }

    private boolean validaDestino(String destino) {
        return true;
    }

    private boolean validaHorarioViagem(String inicioViagem, String fimViagem) {
        return true;
    }

    private boolean validaEvento(String evento) {
        return true;
    }

    private boolean validaTipo(String tipo) {
        return true;
    }

}
