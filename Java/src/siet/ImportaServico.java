package siet;

import d3c0de.date.Date;
import d3c0de.date.Time;
import d3c0de.file.File;
import d3c0de.file.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

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
    private static final int CARGO = 9;

    private List<List<String>> listaImportado;
    private int falha, optEscala, index;
    private String erro;
    private Log log;
    private boolean erroFlag, checkEscala;

    public ImportaServico(Log log, boolean cabecalho) {
        iniciaAtributos(log, cabecalho);
        selecionaArquivo();
    }

    private void iniciaAtributos(Log log, boolean cabecalho) {
        this.listaImportado = new ArrayList<>();
        this.log = log;
        this.index = (cabecalho) ? 1 : 0;
        this.falha = 0;
        this.checkEscala = false;
        log.getTextPane().setText("");
    }

    private void selecionaArquivo() {
        String caminhoArquivo = File.getPathFile();
        listaImportado = File.getImportTable(',', caminhoArquivo);
    }

    public void validaImportacao() {
        log.titleMessage("Importação de Arquivo!\n\n");
        for (int i = index; i < listaImportado.size(); i++) {
            erro = "";
            erroFlag = true;
            checkAllValidate(listaImportado.get(i));
            imprimeErro();
            contadorFalha();
        }
        imprimeTotalFalhas();
    }

    private void imprimeTotalFalhas() {
        if (falha != 0) {
            log.erroMessage("\nERRO - " + falha + " serviço(s) falho(s)!");
        }
    }

    public void importaArquivo() {
        if (falha == 0) {
            List<Map<String, String>> listaServico = geraArquivo();
            for (Map<String, String> servico : listaServico) {
                DbEscServicos.insereServico(servico);
            }
            log.sucessMessage("\nARQUIVO IMPORTADO COM SUCESSO!\n");
            log.infoMessage("Total: " + listaServico.size() + " serviços");
        }
    }

    private List<Map<String, String>> geraArquivo() {
        if (falha == 0) {
            List<Map<String, String>> listaServico = new ArrayList();
            for (int i = index; i < listaImportado.size(); i++) {
                Map<String, String> servico = new HashMap();
                servico.put(DbEscServicos.LINHA, DbGeralLinha.getIdByLinha(listaImportado.get(i).get(LINHA)));
                servico.put(DbEscServicos.ORIGEM, DbGeralPonto.getIdByDescricao(listaImportado.get(i).get(ORIGEM)));
                servico.put(DbEscServicos.DESTINO, DbGeralPonto.getIdByDescricao(listaImportado.get(i).get(DESTINO)));
                servico.put(DbEscServicos.INICIO_VIAGEM, listaImportado.get(i).get(INICIO_VIAGEM));
                servico.put(DbEscServicos.FIM_VIAGEM, listaImportado.get(i).get(FIM_VIAGEM));
                servico.put(DbEscServicos.ATIVO, "1");
                servico.put(DbEscServicos.ID_ESCALA, DbEscEscala.getIdByCodEscala(listaImportado.get(i).get(ESCALA)));
                servico.put(DbEscServicos.ID_EVENTO, listaImportado.get(i).get(EVENTO));
                servico.put(DbEscServicos.ID_PERIODO, "17");
                servico.put(DbEscServicos.ID_TIPO_SERVICO, DbEscTipoServico.getIdByApelido(listaImportado.get(i).get(TIPO)));
                servico.put(DbEscServicos.INICIO_VIGENCIA, new Date().getDbDate());
                servico.put(DbEscServicos.FIM_VIGENCIA, new Date().addDay(5).getDbDate());
                servico.put(DbEscServicos.ID_CARGO, listaImportado.get(i).get(CARGO));
                listaServico.add(servico);
            }
            return listaServico;
        }
        return null;
    }

    private void imprimeErro() {
        if (!erro.equals("")) {
            log.erroMessage("  --  FALHA ");
            log.infoMessage(erro + "\n");
        } else {
            log.sucessMessage("  --  OK \n");
        }
    }

    private void contadorFalha() {
        if (erroFlag == false) {
            falha++;
        }
    }

    private void checkAllValidate(List lista) {
        validaEscala(lista.get(ESCALA).toString());
        validaLinha(lista.get(LINHA).toString());
        validaSentido(lista.get(SENTIDO).toString());
        validaOrigem(lista.get(ORIGEM).toString());
        validaDestino(lista.get(DESTINO).toString());
        validaHorarioViagem(lista.get(INICIO_VIAGEM).toString(), lista.get(FIM_VIAGEM).toString());
        validaEvento(lista.get(EVENTO).toString());
        validaTipo(lista.get(TIPO).toString());
        validaCargo(lista.get(CARGO).toString());
    }

    private void opcaoEscala(String escala) {
        if (!checkEscala) {
            JCheckBox checkbox = new JCheckBox("Fazer esse procedimento para todas as escalas.");
            String msg = "Escala " + escala + " não cadastrada, deseja cadastrar?";
            Object[] params = {msg, checkbox};
            optEscala = JOptionPane.showConfirmDialog(log.getTextPane().getRootPane(), params,
                    "Alerta!", JOptionPane.YES_NO_OPTION);
            checkEscala = checkbox.isSelected();
        }
    }

    private boolean verificaVazio(String campo, String msg) {
        if (campo.equals("")) {
            log.erroMessage("-\t");
            erro += "[" + msg + " não cadastrada] ";
            erroFlag = false;
            return true;
        }
        return false;
    }

    private void validaEscala(String escala) {
        if (!verificaVazio(escala, "Escala")) {
            if (!DbEscEscala.escalaExiste(escala)) {
                opcaoEscala(escala);
                if (optEscala == 0) {
                    log.normalMessage(escala + "\t");
                    DbEscEscala.inserirEscalaDb(escala, "1");
                } else {
                    log.erroMessage(escala + "\t");
                    erro += "[Escala não cadastrada] ";
                    erroFlag = false;
                }
            } else {
                log.normalMessage(escala + "\t");
            }
        }
    }

    private void validaLinha(String linha) {
        if (!verificaVazio(linha, "Linha")) {
            if (!DbGeralLinha.linhaExiste(linha)) {
                log.erroMessage(linha + "\t");
                erro += "[Linha não cadastrada] ";
                erroFlag = false;
            } else {
                log.normalMessage(linha + "\t");
            }
        }
    }

    private void validaSentido(String sentido) {
        if (!verificaVazio(sentido, "Sentido")) {
            if (!DbGeralSentido.sentidoExiste(sentido)) {
                log.erroMessage(sentido + "\t");
                erro += "[Sentido não existe] ";
                erroFlag = false;
            } else {
                log.normalMessage(sentido + "\t");
            }
        }
    }

    private void validaOrigem(String origen) {
        if (!verificaVazio(origen, "Origem")) {
            if (!DbGeralPonto.pontoExiste(origen)) {
                log.erroMessage(origen + "\t");
                erro += "[Ponto de Origem não existe] ";
                erroFlag = false;
            } else {
                log.normalMessage(origen + "\t");
            }
        }
    }

    private void validaDestino(String destino) {
        if (!verificaVazio(destino, "Destino")) {
            if (!DbGeralPonto.pontoExiste(destino)) {
                log.erroMessage(destino + "\t");
                erro += "[Ponto de Destino não existe] ";
                erroFlag = false;
            } else {
                log.normalMessage(destino + "\t");
            }
        }
    }

    private Time validateTime(String horarioViagem) {
        Time time;
        try {
            time = new Time(horarioViagem);
            return time;
        } catch (Exception ex) {
            return null;
        }
    }

    private boolean imprimeTempoVazio(Time time, String msg) {
        if (time == null) {
            log.erroMessage("-\t");
            erro += "[Horário " + msg + " é inválido] ";
            erroFlag = false;
            return false;
        }
        return true;
    }

    private boolean imprimeTempoMenor(Time inicio, Time fim) {
        if (inicio.isMoreThan(fim) && inicio.getHour() < 22) {
            log.erroMessage(inicio.getTime(true) + "\t" + fim.getTime(true) + "\t");
            erro += "[Horário inicial é maior que final] ";
            erroFlag = false;
            return false;
        }
        return true;
    }

    private void validaHorarioViagem(String inicioViagem, String fimViagem) {
        Time inicio = validateTime(inicioViagem);
        Time fim = validateTime(fimViagem);
        boolean bAmbos = true;
        if (inicio != null && fim != null) {
            bAmbos = imprimeTempoMenor(inicio, fim);
        }
        if (imprimeTempoVazio(inicio, "inicial") && bAmbos) {
            log.normalMessage(inicioViagem + "\t");
        }
        if (imprimeTempoVazio(fim, "final") && bAmbos) {
            log.normalMessage(fimViagem + "\t");
        }
    }

    private void validaEvento(String evento) {
        if (!verificaVazio(evento, "Evento")) {
            if (!DbEscEvento.eventoExiste(evento)) {
                log.erroMessage(evento + "\t");
                erro += "[Evento não existe] ";
                erroFlag = false;
            } else {
                log.normalMessage(evento + "\t");
            }
        }
    }

    private void validaTipo(String tipo) {
        if (!verificaVazio(tipo, "Tipo")) {
            if (!DbEscTipoServico.tipoExiste(tipo)) {
                log.erroMessage(tipo + "\t");
                erro += "[Tipo de Serviço não existe] ";
                erroFlag = false;
            } else {
                log.normalMessage(tipo + "\t");
            }
        }
    }

    private void validaCargo(String cargo) {
        try {
            if (Integer.parseInt(cargo) < 1 || Integer.parseInt(cargo) > 2) {
                messageErroCargo(cargo);
            } else {
                log.normalMessage(cargo + " ");
            }
        } catch (NumberFormatException ex) {
            messageErroCargo(cargo);
        }
    }

    private void messageErroCargo(String cargo) {
        log.erroMessage(cargo + " ");
        erro += "[Código de cargo inválido (1 - Motorista) (2- Cobrador)] ";
        erroFlag = false;
    }

}
