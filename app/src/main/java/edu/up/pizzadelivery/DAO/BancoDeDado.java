package edu.up.pizzadelivery.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.up.pizzadelivery.model.Bebida;
import edu.up.pizzadelivery.model.Borda;
import edu.up.pizzadelivery.model.Endereco;
import edu.up.pizzadelivery.model.FormaPagamento;
import edu.up.pizzadelivery.model.Login;
import edu.up.pizzadelivery.model.Pedido;
import edu.up.pizzadelivery.model.Sabor;
import edu.up.pizzadelivery.model.Tamanho;
import edu.up.pizzadelivery.model.Usuario;

public class BancoDeDado extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "PizzaDelivery.db";
    private static final int VERSAO_BANCO = 1;

    private static final String TIPO_TEXTO = " TEXT";
    private static final String TIPO_INTEIRO = " INTEGER";
    private static final String TIPO_REAL = " REAL";
    private static final String VIRGULA = ", ";

    public BancoDeDado(Context context) {

        super(context, NOME_BANCO, null, VERSAO_BANCO);

    }

    //   ############################################################
    //   ####### STRING DE CRIACAO DAS TABELAS #####################
    //   ###########################################################
    private static final String SQL_CRIAR_TABELA_USUARIO =
            "CREATE TABLE IF NOT EXISTS " +
                    Contrato.TabelaUsuario.NOME_DA_TABELA + "(" +
                    Contrato.TabelaUsuario.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaUsuario.COLUNA_EMAIL + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaUsuario.COLUNA_NOME + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaUsuario.COLUNA_CPF + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaUsuario.COLUNA_TELEFONE + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaUsuario.COLUNA_SENHA + TIPO_TEXTO +
                    ")";  //FK


    private static final String SQL_CRIAR_TABELA_ENDERECO =
            "CREATE TABLE IF NOT EXISTS " +
                    Contrato.TabelaEndereco.NOME_DA_TABELA + "(" +
                    Contrato.TabelaEndereco.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaEndereco.COLUNA_CEP + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaEndereco.COLUNA_BAIRRO + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaEndereco.COLUNA_RUA + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaEndereco.COLUNA_NUMERO + TIPO_INTEIRO + VIRGULA +
                    Contrato.TabelaEndereco.COLUNA_CIDADE + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaEndereco.COLUNA_COMPLEMENTO + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaEndereco.COLUNA_USUARIOID + TIPO_INTEIRO + VIRGULA +
                    " FOREIGN KEY (" + Contrato.TabelaEndereco.COLUNA_USUARIOID + ")" +
                    " REFERENCES " + Contrato.TabelaUsuario.NOME_DA_TABELA +
                    "(" + Contrato.TabelaUsuario.COLUNA_ID + ")" + ")";

    private static final String SQL_CRIAR_TABELA_BEBIDA =
            "CREATE TABLE IF NOT EXISTS " +
                    Contrato.TabelaBebida.NOME_DA_TABELA + "(" +
                    Contrato.TabelaBebida.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaBebida.COLUNA_NOME + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaBebida.COLUNA_QTD + TIPO_INTEIRO + VIRGULA +
                    Contrato.TabelaBebida.COLUNA_PRECO + TIPO_REAL + ")";

    private static final String SQL_CRIAR_TABELA_BORDA =
            "CREATE TABLE IF NOT EXISTS " +
                    Contrato.TabelaBorda.NOME_DA_TABELA + "(" +
                    Contrato.TabelaBorda.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaBorda.COLUNA_NOME + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaBorda.COLUNA_PRECO + TIPO_REAL + ")";


    private static final String SQL_CRIAR_TABELA_SABOR =
            "CREATE TABLE IF NOT EXISTS " +
                    Contrato.TabelaSabor.NOME_DA_TABELA + "(" +
                    Contrato.TabelaSabor.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaSabor.COLUNA_NOME + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaSabor.COLUNA_DESCRICAO + TIPO_TEXTO + ")";


    private static final String SQL_CRIAR_TABELA_TAMANHO =
            "CREATE TABLE IF NOT EXISTS " +
                    Contrato.TabelaTamanho.NOME_DA_TABELA + "(" +
                    Contrato.TabelaTamanho.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaTamanho.COLUNA_NOME + TIPO_TEXTO + VIRGULA +
                    Contrato.TabelaTamanho.COLUNA_QTDSABOR + TIPO_INTEIRO + VIRGULA +
                    Contrato.TabelaTamanho.COLUNA_PRECO + TIPO_REAL + ")";


    private static final String SQL_CRIAR_TABELA_FORMAPAGAMENTO =
            "CREATE TABLE IF NOT EXISTS " +
                    Contrato.TabelaFormaPagamento.NOME_DA_TABELA + "(" +
                    Contrato.TabelaFormaPagamento.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaFormaPagamento.COLUNA_NOME + TIPO_TEXTO + ")";


    private static final String SQL_CRIAR_TABELA_PIZZA =
            "CREATE TABLE IF NOT EXISTS " +
                    Contrato.TabelaPizza.NOME_DA_TABELA + "(" +
                    Contrato.TabelaPizza.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaPizza.COLUNA_TAMANHO + TIPO_INTEIRO + VIRGULA + // fk tamanho
                    Contrato.TabelaPizza.COLUNA_BORDA + TIPO_INTEIRO + VIRGULA +//FK

                    " FOREIGN KEY (" + Contrato.TabelaPizza.COLUNA_TAMANHO + ")" +
                    " REFERENCES " + Contrato.TabelaTamanho.NOME_DA_TABELA +
                    "(" + Contrato.TabelaTamanho.COLUNA_ID + ")" + VIRGULA +

                    " FOREIGN KEY (" + Contrato.TabelaPizza.COLUNA_BORDA + ")" +
                    " REFERENCES " + Contrato.TabelaBorda.NOME_DA_TABELA +
                    "(" + Contrato.TabelaBorda.COLUNA_ID + ")"
                    + ")";


    private static final String SQL_CRIAR_TABELA_PIZZAPEDIDA =
            "CREATE TABLE IF NOT EXISTS " +
                    Contrato.TabelaPizzaPedida.NOME_DA_TABELA + "(" +
                    Contrato.TabelaPizzaPedida.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaPizzaPedida.COLUNA_PIZZA + TIPO_INTEIRO + VIRGULA + //FK pizza
                    Contrato.TabelaPizzaPedida.COLUNA_SABOR + TIPO_INTEIRO + VIRGULA + //FK sabor

                    " FOREIGN KEY (" + Contrato.TabelaPizzaPedida.COLUNA_PIZZA + ")" +
                    " REFERENCES " + Contrato.TabelaPizza.NOME_DA_TABELA +
                    "(" + Contrato.TabelaPizza.COLUNA_ID + ")" + VIRGULA +

                    " FOREIGN KEY (" + Contrato.TabelaPizzaPedida.COLUNA_SABOR + ")" +
                    " REFERENCES " + Contrato.TabelaSabor.NOME_DA_TABELA +
                    "(" + Contrato.TabelaSabor.COLUNA_ID + ")" + ")";

    private static final String SQL_CRIAR_TABELA_ITEMPEDIDO =
            "CREATE TABLE IF NOT EXISTS " +
                    Contrato.TabelaItemPedido.NOME_DA_TABELA + "(" +
                    Contrato.TabelaItemPedido.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaItemPedido.COLUNA_PIZZAPEDIDA + TIPO_INTEIRO + VIRGULA + //FK
                    Contrato.TabelaItemPedido.COLUNA_BEBIDA + TIPO_INTEIRO + VIRGULA +//FK
                    Contrato.TabelaItemPedido.COLUNA_PEDIDO + TIPO_INTEIRO + VIRGULA +//FK
                    Contrato.TabelaItemPedido.COLUNA_QUANTIDADE + TIPO_INTEIRO + VIRGULA +
                    Contrato.TabelaItemPedido.COLUNA_SUBTOTAL + TIPO_REAL + VIRGULA +
                    Contrato.TabelaItemPedido.COLUNA_PRECOPEDIDO + TIPO_REAL + VIRGULA +
                    //declaracao fks
                    " FOREIGN KEY (" + Contrato.TabelaItemPedido.COLUNA_PIZZAPEDIDA + ")" +
                    " REFERENCES " + Contrato.TabelaPizzaPedida.NOME_DA_TABELA +
                    "(" + Contrato.TabelaPizzaPedida.COLUNA_ID + ")" + VIRGULA + /// fk tb pizza pedida

                    " FOREIGN KEY (" + Contrato.TabelaItemPedido.COLUNA_PEDIDO + ")" +
                    " REFERENCES " + Contrato.TabelaPedido.NOME_DA_TABELA +
                    "(" + Contrato.TabelaPedido.COLUNA_ID + ")" + VIRGULA + // fk id pedido

                    " FOREIGN KEY (" + Contrato.TabelaItemPedido.COLUNA_BEBIDA + ")" +
                    " REFERENCES " + Contrato.TabelaBebida.NOME_DA_TABELA +
                    "(" + Contrato.TabelaBebida.COLUNA_ID + ")" + ")"; // fk tbm bebida


    private static final String SQL_CRIAR_TABELA_PEDIDO =
            "CREATE TABLE IF NOT EXISTS " +
                    Contrato.TabelaPedido.NOME_DA_TABELA + "(" +
                    Contrato.TabelaPedido.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.TabelaPedido.COLUNA_USUARIO + TIPO_INTEIRO + VIRGULA + //FK
                    Contrato.TabelaPedido.COLUNA_FORMA_PAGAMENTO + TIPO_INTEIRO + VIRGULA +//FK
                    //  Contrato.TabelaPedido.COLUNA_ENDERECO + TIPO_INTEIRO + VIRGULA +
                    Contrato.TabelaPedido.COLUNA_DATA + TIPO_TEXTO + VIRGULA +
                    " FOREIGN KEY (" + Contrato.TabelaPedido.COLUNA_USUARIO + ")" +
                    " REFERENCES " + Contrato.TabelaUsuario.NOME_DA_TABELA +
                    "(" + Contrato.TabelaUsuario.COLUNA_ID + ")" + VIRGULA +

                    " FOREIGN KEY (" + Contrato.TabelaPedido.COLUNA_FORMA_PAGAMENTO + ")" +
                    " REFERENCES " + Contrato.TabelaFormaPagamento.NOME_DA_TABELA +
                    "(" + Contrato.TabelaFormaPagamento.COLUNA_ID + ")" + ")";
//                    analisar como sera feito o rekacionamento
//                    + VIRGULA +
//                    " FOREIGN KEY (" + Contrato.TabelaPedido.COLUNA_ENDERECO + ")" +
//                    " REFERENCES " + Contrato.TabelaEndereco.NOME_DA_TABELA +
//                    "(" + Contrato.TabelaEndereco.COLUNA_ID + ")"

    //    insere tamanhos
    private static final String SQL_INSERIR_BROTO = "INSERT INTO " +
            Contrato.TabelaTamanho.NOME_DA_TABELA +
            "(Nome, QtdSabor, Preco) VALUES ('BROTO', 1, 15)";

    private static final String SQL_INSERIR_MEDIA = "INSERT INTO " +
            Contrato.TabelaTamanho.NOME_DA_TABELA +
            "(Nome, QtdSabor, Preco) VALUES ('MÉDIA', 2, 22)";


    private static final String SQL_INSERIR_GRANDE = "INSERT INTO " +
            Contrato.TabelaTamanho.NOME_DA_TABELA +
            "(Nome, QtdSabor, Preco) VALUES ('GRANDE', 3, 30)";

    private static final String SQL_INSERIR_BIG = "INSERT INTO " +
            Contrato.TabelaTamanho.NOME_DA_TABELA +
            "(Nome, QtdSabor, Preco) VALUES ('BIG', 4, 40)";

    /// insere formas de pagamentos

    private static final String SQL_INSERIR_FP_DIN = "INSERT INTO " +
            Contrato.TabelaFormaPagamento.NOME_DA_TABELA +
            "(Nome) VALUES ('DINHEIRO')";

    private static final String SQL_INSERIR_FP_CDT = "INSERT INTO " +
            Contrato.TabelaFormaPagamento.NOME_DA_TABELA +
            "(Nome) VALUES ('CRÉDITO')";

    private static final String SQL_INSERIR_FP_DBT = "INSERT INTO " +
            Contrato.TabelaFormaPagamento.NOME_DA_TABELA +
            "(Nome) VALUES ('DÉBITO')";

    //insere bebidas


    private static final String SQL_INSERIR_BEBIDA_CC = "INSERT INTO " +
            Contrato.TabelaBebida.NOME_DA_TABELA +
            "(Nome, Preco) VALUES ('COCA-COLA', 9)";

    private static final String SQL_INSERIR_BEBIDA_FL = "INSERT INTO " +
            Contrato.TabelaBebida.NOME_DA_TABELA +
            "(Nome, Preco) VALUES ('FANTA LARANJA 2L', 7)";

    private static final String SQL_INSERIR_BEBIDA_GA = "INSERT INTO " +
            Contrato.TabelaBebida.NOME_DA_TABELA +
            "(Nome, Preco) VALUES ('GUARANÁ 2L', 7)";

    private static final String SQL_INSERIR_BEBIDA_SN = "INSERT INTO " +
            Contrato.TabelaBebida.NOME_DA_TABELA +
            "(Nome, Preco) VALUES ('S/N', '')";

    // insere bordas

    private static final String SQL_INSERIR_BORDA_CH = "INSERT INTO " +
            Contrato.TabelaBorda.NOME_DA_TABELA +
            "(Nome, Preco) VALUES ('CHEDDAR', 4)";

    private static final String SQL_INSERIR_BORDA_CT = "INSERT INTO " +
            Contrato.TabelaBorda.NOME_DA_TABELA +
            "(Nome, Preco) VALUES ('CATUPIRY', 4)";

    private static final String SQL_INSERIR_BORDA_CP = "INSERT INTO " +
            Contrato.TabelaBorda.NOME_DA_TABELA +
            "(Nome, Preco) VALUES ('CHOCOLATE PRETO', 5)";

    private static final String SQL_INSERIR_BORDA_SN = "INSERT INTO " +
            Contrato.TabelaBorda.NOME_DA_TABELA +
            "(Nome, Preco) VALUES ('NORMAL', 0)";

//    // insere sabores

    private static final String SQL_INSERIR_SABOR_FC = "INSERT INTO " +
            Contrato.TabelaSabor.NOME_DA_TABELA +
            "(Nome, Descricao) VALUES ('FRANGO C/ CATUPIRY', 'MOLHO DE TOMATE, MUSSARELA, FRANGO DESFIADO AO MOLHO DE CATUPIRY' )";

    private static final String SQL_INSERIR_SABOR_CA = "INSERT INTO " +
            Contrato.TabelaSabor.NOME_DA_TABELA +
            "(Nome, Descricao) VALUES ('CALABRESA', 'MOLHO DE TOMATE, MUSSARELA E CALABRESA')";

    private static final String SQL_INSERIR_SABOR_PI = "INSERT INTO " +
            Contrato.TabelaSabor.NOME_DA_TABELA +
            "(Nome, Descricao) VALUES ('PIZZAIOLO', 'MOLHO DE TOMATE, MUSSARELA, FRANGO DESFIADO AO MOLHO, PALMITO, MILHO, CALABRESA E CATUPIRY')";

    private static final String SQL_INSERIR_SABOR_RO = "INSERT INTO " +
            Contrato.TabelaSabor.NOME_DA_TABELA +
            "(Nome, Descricao) VALUES ('PARMEGIANA', 'MOLHO DE TOMATE, PRESUNTO, MOLHO PARMEGIANA, PARMESÃO E MUSSARELA')";

    private static final String SQL_INSERIR_SABOR_MA = "INSERT INTO " +
            Contrato.TabelaSabor.NOME_DA_TABELA +
            "(Nome, Descricao) VALUES ('MARGUERITA', 'MOLHO DE TOMATE, TOMATES, PARMESÃO, MANJERICÃO, AZEITE DE OLIVA E MUSSARELA')";

    private static final String SQL_INSERIR_SABOR_TO = "INSERT INTO " +
            Contrato.TabelaSabor.NOME_DA_TABELA +
            "(Nome, Descricao) VALUES ('TOSCANA','MOLHO DE TOMATE, MUSSARELA, CALABRESA E OVOS')";

    private static final String SQL_INSERIR_SABOR_PO = "INSERT INTO " +
            Contrato.TabelaSabor.NOME_DA_TABELA +
            "(Nome, Descricao) VALUES ('PORTUGUESA', 'MOLHO DE TOMATE, PRESUNTO, MUSSARELA, OVOS, CEBOLA E AZEITONAS')";

    private static final String SQL_INSERIR_SABOR_4Q = "INSERT INTO " +
            Contrato.TabelaSabor.NOME_DA_TABELA +
            "(Nome, Descricao) VALUES ('DO CHEFF', 'MOLHO DE TOMATE, MUSSARELA, PALMITO, CHAMPIGNON E MOLHO BRANCO')";

    private static final String SQL_INSERIR_SABOR_LO = "INSERT INTO " +
            Contrato.TabelaSabor.NOME_DA_TABELA +
            "(Nome, Descricao) VALUES ('LOMBO C/ CATUPIRY', 'MOLHO DE TOMATE, LOMBO, MUSSARELA E CATUPIRY')";

    private static final String SQL_INSERIR_SABOR_BO = "INSERT INTO " +
            Contrato.TabelaSabor.NOME_DA_TABELA +
            "(Nome, Descricao) VALUES ('BOLONHESA', 'MOLHO DE TOMATE, BOLONHESA, MOLHO PARMEGIANA, PARMESÃO E MUSSARELA')";

    private static final String SQL_INSERIR_SABOR_BA = "INSERT INTO " +
            Contrato.TabelaSabor.NOME_DA_TABELA +
            "(Nome, Descricao) VALUES ('BACON CROCANTE', 'MOLHO DE TOMATE, BACON, BATATA PALHA E MUSSARELA')";
//    //

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CRIAR_TABELA_BEBIDA);
        db.execSQL(SQL_CRIAR_TABELA_USUARIO);
        db.execSQL(SQL_CRIAR_TABELA_ENDERECO);
        db.execSQL(SQL_CRIAR_TABELA_BORDA);
        db.execSQL(SQL_CRIAR_TABELA_FORMAPAGAMENTO);
        db.execSQL(SQL_CRIAR_TABELA_TAMANHO);
        db.execSQL(SQL_CRIAR_TABELA_SABOR);
        db.execSQL(SQL_CRIAR_TABELA_PIZZA);
        db.execSQL(SQL_CRIAR_TABELA_ITEMPEDIDO);
        db.execSQL(SQL_CRIAR_TABELA_PEDIDO);
        db.execSQL(SQL_CRIAR_TABELA_PIZZAPEDIDA);

        //insere dados ao criar banco
        //Log.i("Criar banco", SQL_INSERIR_BROTO);
        //TAMANHO
        db.execSQL(SQL_INSERIR_BROTO);
        db.execSQL(SQL_INSERIR_MEDIA);
        db.execSQL(SQL_INSERIR_GRANDE);
        db.execSQL(SQL_INSERIR_BIG);
        //FORMA PAGAMENTO
        db.execSQL(SQL_INSERIR_FP_CDT);
        db.execSQL(SQL_INSERIR_FP_DBT);
        db.execSQL(SQL_INSERIR_FP_DIN);
        db.execSQL(SQL_INSERIR_BORDA_SN);
        //BEBIDA
        db.execSQL(SQL_INSERIR_BEBIDA_CC);
        db.execSQL(SQL_INSERIR_BEBIDA_FL);
        db.execSQL(SQL_INSERIR_BEBIDA_GA);
        db.execSQL(SQL_INSERIR_BEBIDA_SN);
        //BORDA
        db.execSQL(SQL_INSERIR_BORDA_CH);
        db.execSQL(SQL_INSERIR_BORDA_CT);
        db.execSQL(SQL_INSERIR_BORDA_CP);
        //SABORES
        db.execSQL(SQL_INSERIR_SABOR_FC);
        db.execSQL(SQL_INSERIR_SABOR_4Q);
        db.execSQL(SQL_INSERIR_SABOR_CA);
        db.execSQL(SQL_INSERIR_SABOR_MA);
        db.execSQL(SQL_INSERIR_SABOR_PI);
        db.execSQL(SQL_INSERIR_SABOR_PO);
        db.execSQL(SQL_INSERIR_SABOR_RO);
        db.execSQL(SQL_INSERIR_SABOR_TO);
        db.execSQL(SQL_INSERIR_SABOR_BA);
        db.execSQL(SQL_INSERIR_SABOR_BO);
        db.execSQL(SQL_INSERIR_SABOR_LO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long CadastrarUsuario(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        // PRIMEIRO FAZER A INSERÇÃO NA TABELA DE USUARIO PRA DEPOIS
        //PEGAR O USUARIO ID E COLOCAR DENTRO DA TABELA ENDERECO .....
        values.put(Contrato.TabelaUsuario.COLUNA_NOME, usuario.getNome());
        values.put(Contrato.TabelaUsuario.COLUNA_EMAIL, usuario.getEmail());
        values.put(Contrato.TabelaUsuario.COLUNA_CPF, usuario.getCpf());
        values.put(Contrato.TabelaUsuario.COLUNA_TELEFONE, usuario.getTelefone());
        values.put(Contrato.TabelaUsuario.COLUNA_SENHA, usuario.getSenha());
        //inseri os dados na tabela usuario

        return db.insert(Contrato.TabelaUsuario.NOME_DA_TABELA, null, values);
    }

    public long CadastrarEndereco(Endereco endereco) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contrato.TabelaEndereco.COLUNA_BAIRRO, endereco.getBairro());
        values.put(Contrato.TabelaEndereco.COLUNA_CEP, endereco.getCep());
        values.put(Contrato.TabelaEndereco.COLUNA_CIDADE, endereco.getCidade());
        values.put(Contrato.TabelaEndereco.COLUNA_COMPLEMENTO, endereco.getComplemento());
        values.put(Contrato.TabelaEndereco.COLUNA_NUMERO, endereco.getNumero());
        values.put(Contrato.TabelaEndereco.COLUNA_RUA, endereco.getRua());
        values.put(Contrato.TabelaEndereco.COLUNA_USUARIOID, endereco.getUsuario().getId());

        return db.insert(Contrato.TabelaEndereco.NOME_DA_TABELA, null, values);
    }

    public Endereco RetornaEndereco(int id){

        Endereco endereco = new Endereco();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " +
                Contrato.TabelaEndereco.NOME_DA_TABELA + " WHERE " +
                Contrato.TabelaEndereco.COLUNA_USUARIOID + " = ?",
                new String[] {String.valueOf(id)});

        if (cursor.moveToFirst()) {
                endereco.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Contrato.TabelaEndereco.COLUNA_ID))));
                endereco.setCep(cursor.getString(cursor.getColumnIndex(Contrato.TabelaEndereco.COLUNA_CEP)));
                endereco.setRua(cursor.getString(cursor.getColumnIndex(Contrato.TabelaEndereco.COLUNA_RUA)));
                endereco.setBairro(cursor.getString(cursor.getColumnIndex(Contrato.TabelaEndereco.COLUNA_BAIRRO)));
                endereco.setCidade(cursor.getString(cursor.getColumnIndex(Contrato.TabelaEndereco.COLUNA_CIDADE)));
                endereco.setNumero(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Contrato.TabelaEndereco.COLUNA_NUMERO))));
                endereco.setComplemento(cursor.getString(cursor.getColumnIndex(Contrato.TabelaEndereco.COLUNA_COMPLEMENTO)));
        }
        return endereco;

    }

    public Usuario RetornaUsuario(String email){
        Usuario lu = new Usuario();
        SQLiteDatabase db =  this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " +
                Contrato.TabelaUsuario.NOME_DA_TABELA + " WHERE " +
                Contrato.TabelaUsuario.COLUNA_EMAIL + " = ?" ,
                new String[]{email});

        if(cursor.moveToFirst()){

             lu.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Contrato.TabelaUsuario.COLUNA_ID))));
             lu.setNome(cursor.getString(cursor.getColumnIndex(Contrato.TabelaUsuario.COLUNA_NOME)));
             lu.setEmail(cursor.getString(cursor.getColumnIndex(Contrato.TabelaUsuario.COLUNA_EMAIL)));
             lu.setCpf(cursor.getString(cursor.getColumnIndex(Contrato.TabelaUsuario.COLUNA_CPF)));
             lu.setTelefone(cursor.getString(cursor.getColumnIndex(Contrato.TabelaUsuario.COLUNA_TELEFONE)));
             lu.setSenha(cursor.getString(cursor.getColumnIndex(Contrato.TabelaUsuario.COLUNA_SENHA)));
             lu.setConfSenha(cursor.getString(cursor.getColumnIndex(Contrato.TabelaUsuario.COLUNA_SENHA)));

        }
        return lu;
    }


    public void deleteEndereco(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Contrato.TabelaEndereco.NOME_DA_TABELA, Contrato.TabelaEndereco.COLUNA_USUARIOID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }
    public void deleteUsuario(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Contrato.TabelaUsuario.NOME_DA_TABELA, Contrato.TabelaUsuario.COLUNA_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    public boolean ValidadaLogin(Login login) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                        Contrato.TabelaUsuario.NOME_DA_TABELA + " WHERE " +
                        Contrato.TabelaUsuario.COLUNA_EMAIL + " = ? AND " +
                        Contrato.TabelaUsuario.COLUNA_SENHA + " = ? ",
                new String[]{login.getEmail(), login.getSenha()});

        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        } else {
            return true;
        }
    }

    public boolean JaCadastrado(String email, String cpf) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                        Contrato.TabelaUsuario.NOME_DA_TABELA + " WHERE " +
                        Contrato.TabelaUsuario.COLUNA_EMAIL + " = ? AND " +
                        Contrato.TabelaUsuario.COLUNA_CPF + " = ? ",
                new String[]{email, cpf});

        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        } else {
            return true;
        }
    }

    //  ########################################################################   ///
    //  ##### METODO PARA RETORNAR FORMAS DE PAGAMENTO EM FORMA DE LISTA #######   ///
    //  ########################################################################   ///
    public ArrayList<FormaPagamento> RetornarFormasPagamento() {
        ArrayList<FormaPagamento> formasPagamento = new ArrayList<FormaPagamento>();
        SQLiteDatabase db = getReadableDatabase();

        String[] colunas = {
                Contrato.TabelaFormaPagamento.COLUNA_ID,
                Contrato.TabelaFormaPagamento.COLUNA_NOME
        };

        Cursor cursor = db.query(Contrato.TabelaFormaPagamento.NOME_DA_TABELA, colunas,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                FormaPagamento fp = new FormaPagamento();
                fp.setNome(cursor.getString(1));
                formasPagamento.add(fp);
            } while (cursor.moveToNext());
        }
        return formasPagamento;
    }

    //  ########################################################################   ///
    //  ##### METODO PARA RETORNAR TAMANHOS DE PIZZA EM FORMA DE LISTA #######   ///
    //  ########################################################################   ///
    public ArrayList<Tamanho> RetornarTamanhos() {
        ArrayList<Tamanho> tamanhos = new ArrayList<Tamanho>();
        SQLiteDatabase db = getReadableDatabase();

        String[] colunas = {
                Contrato.TabelaTamanho.COLUNA_ID,
                Contrato.TabelaTamanho.COLUNA_NOME,
                Contrato.TabelaTamanho.COLUNA_QTDSABOR,
                Contrato.TabelaTamanho.COLUNA_PRECO
        };

        Cursor cursor = db.query(Contrato.TabelaTamanho.NOME_DA_TABELA, colunas,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Tamanho t = new Tamanho();
                t.setId(cursor.getInt(0));
                t.setNome(cursor.getString(1));
                t.setQtdSabores(cursor.getInt(2));
                t.setPreco(cursor.getDouble(3));
                tamanhos.add(t);
            } while (cursor.moveToNext());
        }
        return tamanhos;
    }

    //  ########################################################################   ///
    //  ##### METODO PARA RETORNAR BORDAS DA PIZZA EM FORMA DE LISTA #######   ///
    //  ########################################################################   ///
    public ArrayList<Borda> RetornarBordas() {
        ArrayList<Borda> bordas = new ArrayList<Borda>();
        SQLiteDatabase db = getReadableDatabase();

        String[] colunas = {
                Contrato.TabelaBorda.COLUNA_ID,
                Contrato.TabelaBorda.COLUNA_NOME,
                Contrato.TabelaBorda.COLUNA_PRECO
        };

        Cursor cursor = db.query(Contrato.TabelaBorda.NOME_DA_TABELA, colunas,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Borda b = new Borda();
                b.setId(cursor.getInt(0));
                b.setNome(cursor.getString(1));
                b.setPreco(cursor.getDouble(2));
                bordas.add(b);
            } while (cursor.moveToNext());
        }
        return bordas;
    }


    //  ########################################################################   ///
    //  ##### METODO PARA RETORNAR BEBIDAS EM FORMA DE LISTA #######   ///
    //  ########################################################################   ///
    public ArrayList<Bebida> RetornarBebidas() {
        ArrayList<Bebida> bebidas = new ArrayList<Bebida>();
        SQLiteDatabase db = getReadableDatabase();

        String[] colunas = {
                Contrato.TabelaBebida.COLUNA_ID,
                Contrato.TabelaBebida.COLUNA_NOME,
                Contrato.TabelaBebida.COLUNA_PRECO

        };

        Cursor cursor = db.query(Contrato.TabelaBebida.NOME_DA_TABELA, colunas,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Bebida b = new Bebida();
                b.setId(cursor.getInt(0));
                b.setNome(cursor.getString(1));
                b.setPreco(cursor.getDouble(2));

                bebidas.add(b);
            } while (cursor.moveToNext());
        }
        return bebidas;
    }

    //  ########################################################################   ///
    //  ##### METODO PARA RETORNAR SABOR EM FORMA DE LISTA #######   ///
    //  ########################################################################   ///
    public ArrayList<Sabor> RetornarSabores() {
        ArrayList<Sabor> sabores = new ArrayList<Sabor>();
        SQLiteDatabase db = getReadableDatabase();

        String[] colunas = {
                Contrato.TabelaSabor.COLUNA_ID,
                Contrato.TabelaSabor.COLUNA_NOME,
                Contrato.TabelaSabor.COLUNA_DESCRICAO
        };

        Cursor cursor = db.query(Contrato.TabelaSabor.NOME_DA_TABELA, colunas,
                null, null, null, null,
                Contrato.TabelaSabor.COLUNA_NOME + " ASC", null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Sabor s = new Sabor();
                s.setId((cursor.getInt(0)));
                s.setNome(cursor.getString(1));
                s.setDescricao(cursor.getString(2));
                sabores.add(s);
            } while (cursor.moveToNext());
        }
        return sabores;
    }

    //  ########################################################################   ///
    //  ##### METODO PARA ALTERAR DADOS DO USUARIO #######   ///
    //  ########################################################################   ///

    public long alterarUsuario(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        // DECLARAR DADOS QUE SERAO ALTERADOS


        String condicao = Contrato.TabelaUsuario.COLUNA_ID + " = ?";
        String[] argumentos = {String.valueOf(usuario.getId())

        };

        return db.update(Contrato.TabelaUsuario.NOME_DA_TABELA, values, condicao, argumentos);

    }
    //  ########################################################################   ///
    //  ##### METODO PARA RETORNAR ENCERRAR CONTA DO USUARIO #######   ///
    //  ########################################################################   ///

    public long removerUsuario(Usuario c) {
        SQLiteDatabase db = getWritableDatabase();

        String condicao = Contrato.TabelaUsuario.COLUNA_ID + " = ?";
        String[] argumentos = {
                String.valueOf(c.getId())
        };

        return db.delete(Contrato.TabelaUsuario.NOME_DA_TABELA,
                condicao, argumentos);
    }


    public long CadastrarPedido(Pedido pedido) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        // PRIMEIRO FAZER A INSERÇÃO NA TABELA DE USUARIO PRA DEPOIS
        //PEGAR O USUARIO ID E COLOCAR DENTRO DA TABELA ENDERECO .....
        values.put(Contrato.TabelaPedido.COLUNA_USUARIO, pedido.getUsuario().getId());
        values.put(Contrato.TabelaPedido.COLUNA_DATA, pedido.getData());
        values.put(Contrato.TabelaPedido.COLUNA_FORMA_PAGAMENTO, pedido.getFormaPagamento().getId());

        //inseri os dados na tabela usuario
        return db.insert(Contrato.TabelaPedido.NOME_DA_TABELA, null, values);
    }


}

