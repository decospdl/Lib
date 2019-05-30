package d3c0de.mockup;

import d3c0de.database.ConfigMySql;
import d3c0de.database.ConnectionMySql;
import java.util.Map;

/**
 *
 * @author Andre
 */
public class DbEscServicos {

    public static final String TABLE_NAME = "db_mun.esc_servicos";
    public static final String ID_VIAGEM = "id_viagem";
    public static final String LINHA = "linha";
    public static final String ORIGEM = "origem";
    public static final String DESTINO = "destino";
    public static final String INICIO_VIAGEM = "inicioViagem";
    public static final String FIM_VIAGEM = "fimViagem";
    public static final String ATIVO = "ativo";
    public static final String ID_ESCALA = "id_escala";
    public static final String ID_EVENTO = "id_evento";
    public static final String ID_PERIODO = "id_periodo";
    public static final String ID_TIPO_SERVICO = "id_tipo_servico";
    public static final String INICIO_VIGENCIA = "inicioVigencia";
    public static final String FIM_VIGENCIA = "fimVigencia";
    public static final String ID_CARGO = "id_cargo";

    public static void insereServico(Map<String, String> servico) {
        String query = "INSERT INTO " + TABLE_NAME + "(" + LINHA + ", " + ORIGEM + ", " + DESTINO + ", "
                + INICIO_VIAGEM + ", " + FIM_VIAGEM + ", " + ATIVO + ", " + ID_ESCALA + ", " + ID_EVENTO + ", "
                + ID_PERIODO + ", " + ID_TIPO_SERVICO + ", " + INICIO_VIGENCIA + ", " + FIM_VIGENCIA +", "
                + ID_CARGO + ") VALUES ('"+ servico.get(LINHA) + "','" + servico.get(ORIGEM) + "','" 
                + servico.get(DESTINO) + "','"+ servico.get(INICIO_VIAGEM) + "','" + servico.get(FIM_VIAGEM) 
                + "','" + servico.get(ATIVO) + "','"+ servico.get(ID_ESCALA) + "','" + servico.get(ID_EVENTO) 
                + "','" + servico.get(ID_PERIODO) + "','"+ servico.get(ID_TIPO_SERVICO) + "','" 
                + servico.get(INICIO_VIGENCIA) + "','" + servico.get(FIM_VIGENCIA) + "','" + servico.get(ID_CARGO)+ "')";
        ConnectionMySql.updateDb(ConfigMySql.DB_MUN, query);
    }

}
