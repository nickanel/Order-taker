import java.util.*;
import java.text.*;
import java.io.*;
abstract class Suskeui { //i yperklasi Suskeui
    protected String onoma_modelou;
    protected int etos;
    protected String kataskeuastis;
    protected double timi;
    public void set_onoma_modelou(String s){ onoma_modelou=s;}
    public String get_onoma_modelou() {return onoma_modelou;}
    public void set_etos(int i){ etos=i;}
    public int get_etos () {return etos;}
    public void set_kataskeuastis(String s) {kataskeuastis = s;}
    public String get_kataskeuastis (){ return kataskeuastis;}
    public void set_timi(double p){timi = p;}
    abstract public double get_timi();
    // δημιουργια κατασκευαστή για την κλάση
    public Suskeui (String onoma_modelou, int etos, String kataskeuastis,double timi){
        this.onoma_modelou = onoma_modelou;
        this.etos = etos;
        this.kataskeuastis = kataskeuastis;
        this.timi = timi;
    }
    @Override
    public String toString(){
        String s;
        s="Ονομα μοντελου:"+onoma_modelou+" , Ετος κατασκευης:"+etos+" , Κατασκευαστης:"+
                kataskeuastis+" , Αρχικη τιμη:"+timi;
        return s;
    }
}
abstract class Maures_Suskeues extends Suskeui{
    protected static double pososto_ekptosis=0.0;
    protected String analusi;
    public static void set_pososto_ekptosis(double p){pososto_ekptosis = p;}
    public static double get_pososto_ekptosis() {return pososto_ekptosis;}
    public void set_analusi (String s){ analusi = s;}
    public String get_screenanalusi () {return analusi;}
    public Maures_Suskeues (String onoma_modelou, int etos, String kataskeuastis,
            double timi, String analusi){
        super(onoma_modelou, etos,  kataskeuastis,timi);
        this.analusi = analusi;
    }
    @Override
    public String toString(){
        String s;
        s=super.toString();
        s=s + " , Εκπτωση : "+pososto_ekptosis*100+"% , Αναλυση:"+analusi;
        return s;
    }
}
abstract class Leukes_suskeues extends Suskeui{
    protected static double pososto_ekptosis=0.0;
    protected String energeiaki_klasi;
    public static void set_pososto_ekptosis(double p){pososto_ekptosis = p;}
    public static double get_pososto_ekptosis() {return pososto_ekptosis;}
    public void set_energeiaki_klasi (String ec ) {energeiaki_klasi = ec;}
    public String get_energeiaki_klasi (){ return energeiaki_klasi;}
    public Leukes_suskeues(String onoma_modelou, int etos, String kataskeuastis,
            double timi, String energeiaki_klasi){
            super(onoma_modelou, etos,  kataskeuastis,timi);
        this.energeiaki_klasi = energeiaki_klasi;
    }
    @Override
    public String toString(){
        String s;
        s=super.toString();
        s = s+" , Εκπτωση :"+pososto_ekptosis*100+"% , Ενεργειακη κλαση :"+energeiaki_klasi;
        return s;
    }
}
class TV extends Maures_Suskeues {
    private String typos_modellou;
    private double diastaseis_othonis;
    private String thires_suskeuis;
    // Κατασκευαστής
    public TV (String onoma_modelou, int etos, String kataskeuastis,
                String typos_modellou, double diastaseis_othonis, String screenanalusi,
                String thires_suskeuis, double timi ){
        super(onoma_modelou, etos, kataskeuastis,timi, screenanalusi);
        this.typos_modellou = typos_modellou;
        this.diastaseis_othonis = diastaseis_othonis;
        this.thires_suskeuis = thires_suskeuis;
    }
    public void set_typos_modellou (String s){ typos_modellou = s; }
    public String get_typos_modellou (){ return typos_modellou;}
    public void set_diastaseis_othonis (double s){ diastaseis_othonis = s;}
    public double get_diastaseis_othonis () {return diastaseis_othonis;}
    public void set_thires_suskeuis (String s){ thires_suskeuis=s;};
    public String get_thires_suskeuis() { return thires_suskeuis;};
    @Override
    public double get_timi () { return timi*(1-get_pososto_ekptosis());};
    @Override
    public String toString(){
        String s;
        s=super.toString();
        s = s + " , Τυπος: "+typos_modellou+" , Διαστασεις οθονης:"+diastaseis_othonis+"'' Θυρες :" + thires_suskeuis+
                " , Τελικη τιμη (συμπεριλαμβανομενης της εκπτωσης):"+this.get_timi();
        return s;
    }
}
class DVD extends Maures_Suskeues{
    private String DVDFormat;
    // Κατασκευαστής
    public DVD (String onoma_modelou, int etos, String kataskeuastis,
                String DVDFormat, String analusi, double timi ){
        super(onoma_modelou, etos, kataskeuastis,timi, analusi);
        this.DVDFormat = DVDFormat;
    }
    public void set_DVDFormat (String s){ DVDFormat = s; }
    public String get_DVDFormat (){ return DVDFormat;}
    @Override
    public double get_timi () { return timi*(1-get_pososto_ekptosis());};
    @Override
    public String toString(){
        String s;
        s = super.toString();
        s = s+" , DVD Format:"+DVDFormat+
                " , Τελικη τιμη (συμπεριλαμβανομενης της εκπτωσης):"+this.get_timi();
        return s;
    }
}
class Psugeio extends Leukes_suskeues{
    private String typos_psugeioy;
    private double xoritikotita_sintrisis;
    private double xoritikotita_psuksis;
    // Κατασκευαστής
    public Psugeio (String onoma_modelou, int etos, String kataskeuastis,
                String String, double xoritikotita_sintrisis,
                double xoritikotita_psuksis, String energeiaki_klasi, double timi ){
        super(onoma_modelou, etos,  kataskeuastis, timi,  energeiaki_klasi);
        this.typos_psugeioy = String;
        this.xoritikotita_sintrisis = xoritikotita_sintrisis;
        this.xoritikotita_psuksis = xoritikotita_psuksis;
     }
    public void set_typos_psugeioy (String rt ) { typos_psugeioy = rt;}
    public String get_typos_psugeioy () { return typos_psugeioy;}
    public void set_xoritikotita_sintrisis (double c) { xoritikotita_sintrisis = c;}
    public double get_xoritikotita_sintrisis () {return xoritikotita_sintrisis;}
    public void set_xoritikotita_psuksis (double c) { xoritikotita_psuksis = c;}
    public double get_xoritikotita_psuksis () {return xoritikotita_psuksis;}
    @Override
    public double get_timi () { return timi*(1-get_pososto_ekptosis());};
    @Override
    public String toString(){
        String s;
        s=super.toString();
        s = s+" , Τυπος :"+typos_psugeioy+
                ", Χωρητικοτητα συντηρησης :" +xoritikotita_sintrisis+" , Χωρητικοτητα καταψυξης"+xoritikotita_psuksis+
                " , Τελικη τιμη (συμπεριλαμβανομενης της εκπτωσης):"+this.get_timi();
        return s;
    }
}
class Pluntirio extends Leukes_suskeues{
    private double xoritikotita;
    private double strofes;
    // Κατασκευαστής
    public Pluntirio (String onoma_modelou, int etos, String kataskeuastis,
                double xoritikotita, double strofes, String energeiaki_klasi, double timi ){

        super(onoma_modelou, etos,  kataskeuastis, timi,  energeiaki_klasi);
            this.xoritikotita = xoritikotita;
            this.strofes = strofes;
    }
    public double get_xoritikotita () {return xoritikotita;}
    public void set_xoritikotita (double c) { xoritikotita = c;}
    public double get_strofes () {return strofes;}
    public void set_strofes (double t) { strofes = t;}
    @Override
    public double get_timi () { return timi*(1-get_pososto_ekptosis());};
    @Override
    public String toString(){
        String s;
        s = super.toString();
        s = s+", Χωρητικοτητα :" +xoritikotita+", Στροφες:"+strofes+
        " , Τελικη τιμη (συμπεριλαμβανομενης της εκπτωσης): "+this.get_timi();
        return s;
    }
}
abstract class paraggeliapwlisi <T extends Suskeui> {
    protected int kwdikos;
    protected T Suskeui;
    protected String onoma_pelati;
    protected String tilefwno_pelati;
    protected Date hmerominia;
    protected double kostos;
    // Κατασκευαστής
    public paraggeliapwlisi (T dev, String cn, String ct, Date d){
        Suskeui = dev; onoma_pelati = cn; tilefwno_pelati=ct; hmerominia=d;
        kostos=Suskeui.get_timi(); // το τελικό κόστος υπολογίζεται αυτόματα από τη συσκευή
    }
    public void set_suskeui (T d) { Suskeui = d;}
    public T get_suskeui() {return Suskeui;}
    public void set_onoma_pelati (String cn) { onoma_pelati = cn;}
    public String get_onoma_pelati (){ return onoma_pelati;}
    public void set_tilefwno_pelati (String ct) { tilefwno_pelati = ct;}
    public String get_tilefwno_pelati (){ return tilefwno_pelati;}
    public void set_hmerominia(Date d ) { hmerominia=d;}
    public Date get_hmerominia () { return hmerominia;}
    public int get_kwdikos () { return kwdikos;}
    public double get_kostos() { return kostos;}
    public void set_kostos(double c) { kostos = c;}
    @Override
    public String toString(){
        String s;
        SimpleDateFormat formatter = new SimpleDateFormat("μερα/μηνας/ετος");
        s = "Κωδικος :"+kwdikos+" , Ημερομηνια:"+formatter.format(hmerominia)+" , Προιον : "+Suskeui+", Ονοματεπωνυμο:"+onoma_pelati
                +" ,Τηλεφωνο πελατη:"+tilefwno_pelati+" , Τελικη τιμη:"+kostos;
        return s;
    }
}


class paraggelia <T extends Suskeui> extends paraggeliapwlisi{
    private static int numberOfparaggelias=0;
    private Date expectedDateOfArrival;
    private String orderStatus;
    private static void incNumberofparaggelias(){ numberOfparaggelias++;}
    public paraggelia (T paraggeltheises_suskeui, String onoma_pelati, String tilefwno_pelati,
        Date hmerominiaofparaggeliaPlacement, Date expectedDateOfArrival){
        super(paraggeltheises_suskeui, onoma_pelati, tilefwno_pelati, hmerominiaofparaggeliaPlacement);
        incNumberofparaggelias();
        kwdikos = numberOfparaggelias;
        this.expectedDateOfArrival = expectedDateOfArrival;
        this.orderStatus = "PENDING";
    }
    public Date get_expectedDateOfArrival() { return expectedDateOfArrival;}
    public void set_expectedDateOfArrival(Date d) { expectedDateOfArrival = d;}
    public void set_orderStatus(String o) { orderStatus=o;}
    public String get_orderStatus() { return orderStatus;}
    @Override
    public String toString(){
        SimpleDateFormat formatter = new SimpleDateFormat("μερα/μηνας/ετος");
        String s=super.toString()+" , Αναμενομενη ημερομηνια παραδοσης παραγγελιας:"+formatter.format(expectedDateOfArrival);
        return s;
    }
}

//κλαση για την πραγματοποιηση πωλησης
class pwlisi <T extends Suskeui> extends paraggeliapwlisi{
    private static int arithmos_pwlisewn=0; // Αυτόματη άυξηση κωδικού πώλησης
    // Αύξηση κωδικού πώλησης
    private static void incarithmos_pwlisewn(){ arithmos_pwlisewn++;}
    // Κατασκευαστής
    public pwlisi (T soldsuskeui, String onoma_pelati, String tilefwno_pelati,
            Date hmerominiaofpwlisi){
            super(soldsuskeui, onoma_pelati,  tilefwno_pelati,  hmerominiaofpwlisi);
            incarithmos_pwlisewn();
            this.kwdikos = arithmos_pwlisewn;
    }
}
class diathesimes_suskeues {
    List <Suskeui> Suskeui; // Η λίστα με τις διαθέσιμες συσκευές
    List <Integer> posotita; // Η λίστα των ποσοτήτων τις κάθε διαθέσιμης συσκευής
    public diathesimes_suskeues (){
        Suskeui = new ArrayList <Suskeui>();
        posotita = new ArrayList <Integer>();
    }
    public void addNewsuskeui(Suskeui d, Integer q){
   Suskeui dev =deviceExists(d.get_onoma_modelou(),d.get_etos(),d.get_kataskeuastis());
        if (dev==null){
            Suskeui.add(d);posotita.add(q);
        }
        else
            setsuskeuiposotita(dev,q);
    }
    
    public void setsuskeuiposotita(Suskeui d, Integer q){
        int i;
        i=Suskeui.indexOf(d);
        if (i!=-1) posotita.set(i, q);
        else addNewsuskeui(d,q);
    }
    public int getsuskeuiposotita(Suskeui d){
        int i;
        i = Suskeui.indexOf(d);
        if (i==-1) return -1;
        else return posotita.get(i);
    }
    public int metritis(){ return Suskeui.size();}
    public Suskeui getsuskeui(int i){
        if (i>=0 && i<=Suskeui.size())
            return Suskeui.get(i);
        else
            return null;
    }
    @Override
    public String toString(){
        String s; int i;
        s = "Λιστα διαθεσιμων συσκευων\n";
        s=s+"_________________________\n";
        for(i=0;i<Suskeui.size();i++){
            s=s+i+"\t"+(Suskeui.get(i))+"\t , Ποσοτητα:"+posotita.get(i)+"\n";
        }
        return s;
    }
    //μεθοδος για την ανακτηση της συσκευης
    public Suskeui get_Suskeui(int i){
        if (i>=0 && i<=Suskeui.size())
            return Suskeui.get(i);
        else
            return null;      

}
     public Suskeui deviceExists(String onoma_modelou, int etos, String kataskeuastis){
        int i;
        Suskeui dev;
        for (i=0;i<Suskeui.size();i++){
            dev=Suskeui.get(i);
            if (etos!=0) {
                if (dev.get_onoma_modelou().equalsIgnoreCase(onoma_modelou) &&
                    (dev.get_etos()==etos) &&
                    dev.get_kataskeuastis().equalsIgnoreCase(kataskeuastis))
                    return Suskeui.get(i);
            }
            else
                if (dev.get_onoma_modelou().equalsIgnoreCase(onoma_modelou) &&
                    dev.get_kataskeuastis().equalsIgnoreCase(kataskeuastis))
                    return Suskeui.get(i);

        }
        return null;
    }


}
    class ItemParser{
    // Ο πίνακας tags περιέχει τις λέξεις κλειδιά που προσδιορίζουν μια συσκευή
    private String tags[]={"ITEM_TYPE",
    "MODEL","MODEL_YEAR","MANUFACTURER","PRICE","PIECES","PANEL_TYPE",
    "DIMENSIONS","RESOLUTION","INTERFACES","DVD_FORMAT","REFRIGERATOR_TYPE",
    "CONSERVATION_CAPACITY","FROST_CAPACITY","ENERGY_CLASS", "WASHING_CAPACITY", "TURNS"};
    // δηλώνοντια παρόμοιες μεταβλητές με τις λέξεις κλειδιά για να αποθηκεύσουμε
    // τις τιμές των χαρακτηριστικών μιας συσκευής
    // Δεν είναι απαραίτητο οι μεταβλητές να έχουν το ίδιο όνομα με τις λέξεις κλειδιά
    // απλώς το έκανα για λόγους ευκολίας
    private String ITEM_TYPE,MODEL,MANUFACTURER,
                  PANEL_TYPE,RESOLUTION,
                  INTERFACES,DVD_FORMAT,
                  REFRIGERATOR_TYPE,ENERGY_CLASS;
    private int MODEL_YEAR,PIECES;
    private double DIMENSION,PRICE,CONSERVATION_CAPACITY,FROST_CAPACITY,WASHING_CAPACITY, TURNS;
    private String line; // η γραμμή που διαβάζουμε από ένα αρχείο
    private BufferedReader fileinput; // το αντικείμενο του αρχείου εισόδου
    private BufferedWriter fileoutput; // το αντικείμενο του αρχείου εξόδου
    // Ο κατασκευαστής της κλάσης δέχεται σαν όρισμα το αρχείο και
    // αρχικοποιεί τα χαρακτηριστικά σε προκαθορισμένες τιμές
    public ItemParser(BufferedReader fileinput){
            ITEM_TYPE=MODEL=MANUFACTURER=PANEL_TYPE=RESOLUTION=
            INTERFACES=DVD_FORMAT=REFRIGERATOR_TYPE=ENERGY_CLASS="";
            MODEL_YEAR=PIECES=0;
            DIMENSION=PRICE=CONSERVATION_CAPACITY=FROST_CAPACITY=WASHING_CAPACITY=TURNS=0.0;
        this.fileinput = fileinput;
    }
    public ItemParser(BufferedWriter fileoutput){
        this.fileoutput = fileoutput;
    }
    // Η μέθοδος isTag ελέγχει αν ένα αλφαριθμητικό είναι tag
    private boolean  isTag(String s){
        int i;
        for (i=0;i<tags.length;i++)
            if (s.equalsIgnoreCase(tags[i]))
                return true;
        return false;
    }
    // Η μέθοδος readItem διαβάζει το αρχείο και επιστρέφει true αν
    // διαβάστηκε επιτυχώς, αλλιώς false
    private boolean readItem(){
        String variable, value; // variable είναι το χαρακτηριστικό της συσκευής και value η τιμή αυτού
       /* Ο tokenizer είναι μια κλάση της Java που μας επιτρέπει να διαιρούμε
        * ένα αλφαριθμητικό σε μικρότερα με βάση ειδικούς χαρακτήρες π.χ.
         * κόμμα, τελεία, κενό, tab κτλ
         */
        StringTokenizer st;
        try {
            // διαβάζουμε μια γραμμή από το αρχείο
            line = fileinput.readLine();  line=line.trim();
            if (line==null) { // αν είναι τέλος αρχείου είναι λάθος
                System.out.println("Unexpected end of file!");
                fileinput.close();
                return false;
            }
            if (line.equalsIgnoreCase("")){
                System.out.println("Empty lines are not allowed!");
                fileinput.close();
                return false;
            }
            /* Αφού διαβάσουμε τη γραμμή τη χωρίζουμε στα αλφαριθμητικά από
             * τα οποία αποτελείται χρησιμοποιώντας τον tokenizer και δίνοντάς
             * του ως παραμέτρους τη γραμμή και τους χαρακτήρες με βάση τους
             * οποίους θα τη σπάσουμε στα επιμέρους αλφαριθμητικά
             */
            st=new StringTokenizer(line," /t");
            line = st.nextToken();  // παίρνουμε το πρώτο αλφαριθμητικό
            /* Η περιγραφή μιας συσκευής ξεκινά με "{" επομένως αν δεν βρούμε
             * αυτό πρώτο έχουμε λάθος προδιαγραφή αρχείου
             */
            if (!(line.equalsIgnoreCase("{"))){
                System.out.println("Invalid file format. { expected!");
                fileinput.close();
                return false;
            }
            /* Οι επόμενες γραμμές του αρχείου θα πρέπει να περιέχουν τα χαρακτηριστικά
             * της συσκευής και τις τιμές τους
             */
             line = fileinput.readLine();line=line.trim();
             if (line.equalsIgnoreCase("")){
                 System.out.println("Empty lines are not allowed!");
                 fileinput.close();
                 return false;
             }
             /* διαβάζουμε γραμμές μέχρι να συναντήσουμε το } που σηματοδοτεί το
              * τέλος της περιγραφής μιας συσκευής
              */

             while (!line.equalsIgnoreCase("}")){ // Μέχρι να φτάσουμε στο τέλος ενός προϊόντος
                 if (line==null) { // αν τελειώσει το αρχείο πριν δούμε το } έχουμε λάθος
                     System.out.println("Unexpected end of file");
                     fileinput.close();
                     return false;
                 }
                 /* Κάθε γραμμή που διαβάζουμε αποτελείται από δύο αλφαριθμητικά:
                  * μια μεταβλητή και την τιμή της. Η χρήση του tokenizer θα μας
                  * επιτρέψει να διαβάσουμε τη μεταβλητή και τη τιμή της με ευκολία
                  */
                 st=new StringTokenizer(line," /t");
                 variable = st.nextToken(); // διαβάζουμε μια μεταβλητή
                 if (isTag(variable)==true){
                    value = st.nextToken(); // και τη τιμή της
                    // Διάβασμα και ανάθεση μεταβλητών
                    if (variable.equalsIgnoreCase("ITEM_TYPE")) ITEM_TYPE=value;
                    if (variable.equalsIgnoreCase("MODEL")) {
                        while (st.hasMoreTokens())
                            value = value +" "+st.nextToken();
                        MODEL=value;
                     }
                    if (variable.equalsIgnoreCase("MANUFACTURER"))  {
                        while (st.hasMoreTokens())
                            value = value +" "+st.nextToken();
                        MANUFACTURER=value;
                     }
                    if (variable.equalsIgnoreCase("PANEL_TYPE")) PANEL_TYPE=value;
                    if (variable.equalsIgnoreCase("DIMENSIONS")) DIMENSION=Double.parseDouble(value);
                    if (variable.equalsIgnoreCase("RESOLUTION")) RESOLUTION=value;
                    if (variable.equalsIgnoreCase("INTERFACES")){
                        while (st.hasMoreTokens())
                            value = value +" "+st.nextToken();
                        INTERFACES=value;
                     }
                    if (variable.equalsIgnoreCase("DVD_FORMAT")) {
                        while (st.hasMoreTokens())
                            value = value +" "+st.nextToken();
                        DVD_FORMAT=value;
                     }
                    if (variable.equalsIgnoreCase("REFRIGERATOR_TYPE")) REFRIGERATOR_TYPE=value;
                    if (variable.equalsIgnoreCase("ENERGY_CLASS")){
                        while (st.hasMoreTokens())
                            value = value +" "+st.nextToken();
                        ENERGY_CLASS=value;
                     }
                    if (variable.equalsIgnoreCase("MODEL_YEAR")) MODEL_YEAR=Integer.parseInt(value);
                    if (variable.equalsIgnoreCase("PIECES")) PIECES=Integer.parseInt(value);
                    if (variable.equalsIgnoreCase("PRICE")) PRICE=Double.parseDouble(value);
                    if (variable.equalsIgnoreCase("CONSERVATION_CAPACITY")) CONSERVATION_CAPACITY=Double.parseDouble(value);
                    if (variable.equalsIgnoreCase("FROST_CAPACITY")) FROST_CAPACITY=Double.parseDouble(value);
                    if (variable.equalsIgnoreCase("WASHING_CAPACITY")) WASHING_CAPACITY=Double.parseDouble(value);
                    if (variable.equalsIgnoreCase("TURNS")) TURNS=Double.parseDouble(value);
                    line = fileinput.readLine();line=line.trim(); // επόμενη γραμμή
                    if (line.equalsIgnoreCase("")){
                        System.out.println("Empty lines are not allowed!");
                        fileinput.close();
                        return false;
                    }
                 }
                 else{ // αν η μεταβλητή δεν αναγνωρίζεται ως χαρακτηριστικό καμμίας συσκευής
                     System.out.println("Invalid tag '"+variable+"'"); // τότε είναι λάθος το αρχείο
                     fileinput.close();
                     return false;
                }
             }
             /* Ολοκλήρωση διαβάσματος συσκευής, επομένως η τελευταία
              * γραμμή πρέπει να έχει το χαρακτήρα }
              */
             st=new StringTokenizer(line," /t");
             line = st.nextToken();
             if (!line.equalsIgnoreCase("}")){ // αν δεν υπάρχει το } έχουμε λάθος
                 System.out.println("Missing } in item specification.");
                 fileinput.close();
                 return false;
             }
             else{
                 return true;}
         }catch (Exception e){
             System.err.println("Error:"+e.getMessage());
             return false;
         }
    }

    // Η μέθοδος writeDevice εγγράφει μια συσκευή σττο αρχείο εξόδου
    // και επιστρέφει true αν η εγγραφή έγινε επιτυχώς, αλλιώς false
    // Αν η μεταβλητή quantity είναι αρνητική, τότε αυτή χρησιμεύει
    // ως ένδειξη ότι δεν γράφουμε μια διαθέσιμη συσκευή αλλά
    // γράφουμε συσκευή που πωληθηκε ή παραγγέλθηκε οπότε τα tags
    // αρχής και τέλους συσκευής {} δεν πρέπει να γραφτούν στην έξοδο
    public boolean writeDevice(Suskeui d, int quantity){
    if ((d==null) ||
       ((!(d instanceof TV))) &&
       (!(d instanceof DVD)) &&
       (!(d instanceof Psugeio)) &&
       (!(d instanceof Pluntirio)))
            return false;
    try {
        if (quantity>0){
            fileoutput.write("{");fileoutput.newLine();
        }
        if (d instanceof TV){
            fileoutput.write("ITEM_TYPE TV");fileoutput.newLine();
            fileoutput.write("MODEL "+d.get_onoma_modelou());fileoutput.newLine();
            fileoutput.write("MODEL_YEAR "+d.get_etos());fileoutput.newLine();
            fileoutput.write("MANUFACTURER "+d.get_kataskeuastis());fileoutput.newLine();
            fileoutput.write("PRICE "+d.get_timi());fileoutput.newLine();
            fileoutput.write("PANEL_TYPE "+((TV) d).get_typos_modellou());fileoutput.newLine();
            fileoutput.write("DIMENSIONS "+((TV) d).get_diastaseis_othonis());fileoutput.newLine();
            fileoutput.write("RESOLUTION "+((TV) d).get_screenanalusi());fileoutput.newLine();
            fileoutput.write("INTERFACES "+((TV) d).get_thires_suskeuis());fileoutput.newLine();
        }
        if (d instanceof DVD){
            fileoutput.write("ITEM_TYPE DVD");fileoutput.newLine();
            fileoutput.write("MODEL "+d.get_onoma_modelou());fileoutput.newLine();
            fileoutput.write("MODEL_YEAR "+d.get_etos());fileoutput.newLine();
            fileoutput.write("MANUFACTURER "+d.get_kataskeuastis());fileoutput.newLine();
            fileoutput.write("PRICE "+d.get_timi());fileoutput.newLine();
            fileoutput.write("RESOLUTION "+((DVD) d).get_screenanalusi());fileoutput.newLine();
            fileoutput.write("DVD_FORMAT "+((DVD) d).get_DVDFormat());fileoutput.newLine();
        }
        if (d instanceof DVD){
            fileoutput.write("ITEM_TYPE DVD");fileoutput.newLine();
            fileoutput.write("MODEL "+d.get_onoma_modelou());fileoutput.newLine();
            fileoutput.write("MODEL_YEAR "+d.get_etos());fileoutput.newLine();
            fileoutput.write("MANUFACTURER "+d.get_kataskeuastis());fileoutput.newLine();
            fileoutput.write("PRICE "+d.get_timi());fileoutput.newLine();
            fileoutput.write("RESOLUTION "+((DVD) d).get_screenanalusi());fileoutput.newLine();
            fileoutput.write("DVD_FORMAT "+((DVD) d).get_DVDFormat());fileoutput.newLine();
        }
        if (d instanceof Psugeio){
            fileoutput.write("ITEM_TYPE REFRIGERATOR");fileoutput.newLine();
            fileoutput.write("MODEL "+d.get_onoma_modelou());fileoutput.newLine();
            fileoutput.write("MODEL_YEAR "+d.get_etos());fileoutput.newLine();
            fileoutput.write("MANUFACTURER "+d.get_kataskeuastis());fileoutput.newLine();
            fileoutput.write("PRICE "+d.get_timi());fileoutput.newLine();
            fileoutput.write("REFRIGERATOR_TYPE "+((Psugeio) d).get_typos_psugeioy());fileoutput.newLine();
            fileoutput.write("CONSERVATION_CAPACITY "+ ((Psugeio) d).get_xoritikotita_sintrisis());fileoutput.newLine();
            fileoutput.write("FROST_CAPACITY " + ((Psugeio) d).get_xoritikotita_psuksis());fileoutput.newLine();
            fileoutput.write("ENERGY_CLASS "+ ((Psugeio) d).get_energeiaki_klasi());fileoutput.newLine();
        }
        if (d instanceof Pluntirio){
            fileoutput.write("ITEM_TYPE WASHING_MACHINE");fileoutput.newLine();
            fileoutput.write("MODEL "+d.get_onoma_modelou());fileoutput.newLine();
            fileoutput.write("MODEL_YEAR "+d.get_etos());fileoutput.newLine();
            fileoutput.write("MANUFACTURER "+d.get_kataskeuastis());fileoutput.newLine();
            fileoutput.write("PRICE "+d.get_timi());fileoutput.newLine();
            fileoutput.write("WASHING_CAPACITY "+((Pluntirio) d).get_xoritikotita());fileoutput.newLine();
            fileoutput.write("TURNS "+((Pluntirio) d).get_strofes());fileoutput.newLine();
            fileoutput.write("ENERGY_CLASS "+ ((Pluntirio) d).get_energeiaki_klasi());fileoutput.newLine();
        }
        // Αν δεν δώσουμε θετικό quantity τότε
        // γράφουμε τη συσκευή μιας παραγγελίας ή πώλησης και δεν θέλουμε
        // να εμφανίζονται αυτές οι πληροφορίες ούτε να μπει το tag } που σηματοδοτεί
        // το τέλος μιας συσκευής
        if (quantity>0) {
            fileoutput.write("PIECES "+quantity);
            fileoutput.newLine();
            fileoutput.write("}");fileoutput.newLine();
        }
        return true;
     }catch (Exception e){
             System.err.println("Error:"+e.getMessage());

             return false;
       }
    }

    public Suskeui readDevice(){
        Suskeui sys=null; // η συσκευή που διαβάζεται
        if (!readItem()) return null; // αποτυχία διαβάσματος συσκευής
        if (ITEM_TYPE.equalsIgnoreCase("TV")){ // αρχικοποίηση Τηλεόρασης
            sys = new TV(this.MODEL, this.MODEL_YEAR, this.MANUFACTURER,
                this.PANEL_TYPE, this.DIMENSION, this.RESOLUTION,
                this.INTERFACES, this.PRICE );
        }
        if (ITEM_TYPE.equalsIgnoreCase("DVD")){ // αρχικοποίηση DVD
            sys = new DVD(this.MODEL, this.MODEL_YEAR, this.MANUFACTURER,
                this.DVD_FORMAT, this.RESOLUTION, this.PRICE );
        }
        if (ITEM_TYPE.equalsIgnoreCase("Psugeio")){ // αρχικοποίηση ψυγείου
            sys = new Psugeio (this.MODEL, this.MODEL_YEAR, this.MANUFACTURER,
                this.REFRIGERATOR_TYPE, this.CONSERVATION_CAPACITY,
                this.FROST_CAPACITY, this.ENERGY_CLASS, this.PRICE );
        }
        if (ITEM_TYPE.equalsIgnoreCase("Washing_machine")){ // αρχικοποίηση πλυντήριου
            sys = new Pluntirio (this.MODEL, this.MODEL_YEAR, this.MANUFACTURER,
                this.WASHING_CAPACITY, this.TURNS, this.ENERGY_CLASS, this.PRICE );
        }
    return sys; // επιστροφή της συσκευής που διαβάστηκε ή null αν δεν αναγνωρίζεται ο τύπος της
    }
    // η επόμενη μέθοδος επιστρέφει τη διαθεσιμότητα της συσκευής που διαβάστηκε
    public int get_Quantity(){ return this.PIECES;}
    // η επόμενη μέθοδος επιστρέφει το αρχείο, το οποίο βρίσκεται στο σημείο πια
    // που ολοκληρώσαμε το διάβασμα μιας συσκευής
    public BufferedReader get_fileinput(){ return fileinput;}
    public BufferedWriter get_fileoutput(){ return fileoutput;}
    public void set_fileoutput(BufferedWriter f){  fileoutput=f;}
}
    class AvailableDevicesFileParser{
    diathesimes_suskeues devices; // Η εσωτερική λίστα διαθέσιμων συσκευών που διαβάζονται από το αρχείο
    private String filename; // το όνομα του αρχείου
    // Ο κατασκευαστής της κλάσης για ανάγνωση
    public AvailableDevicesFileParser(String fname){
        filename = fname; // αρχικοποίηση των μεταβλητών της κλάσης
        devices = new diathesimes_suskeues();
    }
    // ο κατασκευαστής της κλάσης για εγγραφή
    public AvailableDevicesFileParser(String fname, diathesimes_suskeues devices){
            filename=fname;
            this.devices = devices;
    }
    // Η κύρια μέθοδος της κλάσης που διαβάζει τις συσκευές από το αρχείο
    // και τις αποθηκεύει στην εσωτερική λίστα των διαθέσιμων συσκευών
    public diathesimes_suskeues LoadDevicesFromFile(){
        // κώδικας ελέγχου ύπαρξης αρχείου
        File f=new File(filename);
        if (!f.exists()){
            System.out.println("File not found!");
            return null;
        }
        ItemParser parser; // χρησιμοποιείται για το διάβασμα μιας συσκευής
        int quantity; // η διαθέσιμη ποσότητα μιας συσκευής
        String tag;
        // κώδικας για άνοιγμα αρχείου προς ανάγνωση
        try {
            // ανοίγουμε ένα αρχείο fileinput για ανάγνωσή του
            BufferedReader fileinput = new BufferedReader(new FileReader(filename));
            String line=fileinput.readLine(); // διαβάζω γραμμή από το αρχείο
            line=line.trim(); // απορρίπτω τα κενά πριν και μετά τη γραμμή
            // έλεγχος αν η πρώτη γραμμή του αρχείου είναι το ITEM_LIST
            if (!(line.equalsIgnoreCase("ITEM_LIST"))){ // αν όχι το αρχείο δεν είναι σωστό
                System.out.println("Invalid file format. Not found ITEM_LIST tag");
                fileinput.close(); // κλείσιμο αρχείου
                return null;
            }
            StringTokenizer st = new StringTokenizer(line," /t");
            Suskeui dev=null; // η συσκευή που διαβάζεται κάθε φορά
            line=fileinput.readLine();line=line.trim();
            // Η επόμενη γραμμή πρέπει να περιέχει το { που σηματοδοτεί την έναρξη
            // μιας λίστας συσκευών
            if (!line.equalsIgnoreCase("{")) { // αλλιώς είναι λάθος
                System.out.println("{ expected as beginnning of ITEM_LIST");
                fileinput.close();
                return null;
            }
            line=fileinput.readLine();
            if (line==null) {
                System.out.println("Unexpected end of item list!");
                fileinput.close();
                return null;
            }
            // Αν η γραμμή δεν είναι κενή τότε ξεκινούμε να διαβάζουμε τη λίστα
            // μέχρι να βρούμε το }
            line=line.trim();
            if (line.equalsIgnoreCase("")){
                System.out.println("Empty lines are not allowed!");
                fileinput.close();
                return null;
            }
            st = new StringTokenizer(line," /t");
            while (!st.nextToken().equalsIgnoreCase("}")){
                st = new StringTokenizer(line," /t");
                // Αν η γραμμή που διαβάστηκε έχει το tag ITEM
                // τότε πρόκειται για νέα συσκευή και θα τη διαβάσουμε
                // με το itemParser
                tag=st.nextToken();
                if (tag.equalsIgnoreCase("ITEM")){
                    parser = new ItemParser(fileinput); // διάβασμα συσκευής
                    dev=parser.readDevice();// δημιουργία αντικειμένου
                    if (dev!=null){ // αν η συσκευή δημιουργήθηκε με επιτυχία
                       quantity = parser.get_Quantity(); // ανάκτηση διαθέσιμης ποσότητας
                        devices.addNewsuskeui(dev, quantity); // και προσθήκη στη λίστα
                    }
                    else return null; // τερματισμός διαβάσματος λόγω λάθους στην περιγραφή της συσκευής
                    fileinput = parser.get_fileinput(); // ενημέρωση του δείκτη του αρχείου στη γραμμή που είμαστε μετά το διάβασμα της συσκευής
                    line=fileinput.readLine();
                    line=line.trim();
                    if (line.equalsIgnoreCase("")){
                        System.out.println("Empty lines are not allowed!");
                        fileinput.close();
                        return null;
                    }
                    st = new StringTokenizer(line," /t");
                    if (line==null){  // αν όμως φτάσουμε στο τέλος χωρίς να βρούμε το }
                        System.out.append("Unexpected End of File!!");
                        fileinput.close();
                        return null;
                    }
                }
                else { // αν δεν βρεθεί το ΙΤΕΜ
                       if (!tag.equalsIgnoreCase("}")){
                        System.out.println("ITEM expected but was not found!");
                         fileinput.close();
                         return null;
                    }
                }
            }
            fileinput.close();
            return devices;
        }catch (Exception e){
            System.err.println("Error:"+e.getMessage());
            return null;
        }
    }
   // Η κύρια μέθοδος της κλάσης που γράφει τις συσκευές σε αρχείο
   public boolean WriteDevicesToFile(){
       if (devices==null) return false;
       int i;

       // κώδικας για άνοιγμα αρχείου προς εγγραφή
        try {
            // ανοίγουμε ένα αρχείο fileoutput για εγγραφή του
            BufferedWriter fileoutput = new BufferedWriter(new FileWriter(filename));
            Suskeui d;
            fileoutput.write("ITEM_LIST");fileoutput.newLine();
            fileoutput.write("{");fileoutput.newLine();
            ItemParser writeItem = new ItemParser(fileoutput);
            for (i=0;i<devices.metritis();i++){
                fileoutput.write("ITEM");fileoutput.newLine();
                d=devices.getsuskeui(i);
                writeItem.set_fileoutput(fileoutput);
                if (!writeItem.writeDevice(d, devices.getsuskeuiposotita(d))){
                    System.out.println("An error occured while writing to file. Aborting!");
                    return false;
                }
                fileoutput=writeItem.get_fileoutput();
            }
            fileoutput.write("}");fileoutput.newLine();
            fileoutput.close();
            return true;

        }catch (Exception e){
            System.err.println("Error:"+e.getMessage());
            return false;
        }
   }


}



/* H επόμενη κλάση διαβάζει μια παραγγελία ή πώληση από το αρχείο
 * Η δομή της και η λογική της είναι παρόμοια με την κλάση ItemParser
 */

class OrderSellParser{
    // Ο πίνακας tags περιέχει τις λέξεις κλειδιά που προσδιορίζουν μια παραγγελία
    private String tags[]={"ITEM_TYPE","ORDER_NUMBER","CUSTOMER_NAME",
    "CUSTOMER_TEL", "SALE_NUMBER","DATE","DATE_OF_ARRIVAL",
    "MODEL","MODEL_YEAR","MANUFACTURER","PRICE","PIECES","PANEL_TYPE",
    "DIMENSIONS","RESOLUTION","INTERFACES","DVD_FORMAT","REFRIGERATOR_TYPE",
    "CONSERVATION_CAPACITY","FROST_CAPACITY","ENERGY_CLASS", "WASHING_CAPACITY", "TURNS"};
    // δηλώνοντια παρόμοιες μεταβλητές με τις λέξεις κλειδιά για να αποθηκεύσουμε
    // τις τιμές των χαρακτηριστικών μιας συσκευής
    // Δεν είναι απαραίτητο οι μεταβλητές να έχουν το ίδιο όνομα με τις λέξεις κλειδιά
    // απλώς το έκανα για λόγους ευκολίας
    private String ITEM_TYPE,MODEL,MANUFACTURER,
                  PANEL_TYPE,RESOLUTION,
                  INTERFACES,DVD_FORMAT,
                  REFRIGERATOR_TYPE,ENERGY_CLASS;
    private int MODEL_YEAR,PIECES;
    private double DIMENSION,PRICE,CONSERVATION_CAPACITY,FROST_CAPACITY,WASHING_CAPACITY, TURNS;
    private String CUSTOMER_NAME,CUSTOMER_TEL;
    private int ORDERSELL_NUMBER;
    private Date DATE, DATE_OF_ARRIVAL;
    private String line; // η γραμμή που διαβάζουμε από ένα αρχείο
    private BufferedReader fileinput; // το αντικείμενο του αρχείου εισόδου
    private BufferedWriter fileoutput; // το αντικείμενο του αρχείου εξόδου
    private boolean isOrder; // true αν διαβάζουμε παραγγελία, false αν διαβάζουμε πώληση
    private diathesimes_suskeues devices; // πρόσβαση στις διαθέσιμες συσκευές
    // Ο κατασκευαστής της κλάσης δέχεται σαν όρισμα το αρχείο και
    // αρχικοποιεί τα χαρακτηριστικά σε προκαθορισμένες τιμές
    // Ο κατασκευαστής επίσης δέχεται το προσδιοριστικό isOrder=true αν
    // πρόκειται για διάβασμα παραγγελίας, ή isOrder=false αν πρόκειται για
    // διάβασμα πώλησης
    public OrderSellParser(BufferedReader fileinput, boolean isOrder, diathesimes_suskeues dev){
            ITEM_TYPE=MODEL=MANUFACTURER=PANEL_TYPE=RESOLUTION=
            INTERFACES=DVD_FORMAT=REFRIGERATOR_TYPE=ENERGY_CLASS="";
            MODEL_YEAR=PIECES=0;
            DIMENSION=PRICE=CONSERVATION_CAPACITY=FROST_CAPACITY=WASHING_CAPACITY=TURNS=0.0;
            CUSTOMER_NAME=CUSTOMER_TEL="";
            ORDERSELL_NUMBER=0;DATE=null;DATE_OF_ARRIVAL=null;
            this.fileinput = fileinput;
            this.isOrder=isOrder;
            devices = dev;
    }
    // O Κατασκευαστής της κλάσης για την εγγραφή μιας πώλησης/παραγγελίας
    public OrderSellParser(BufferedWriter fileoutput, boolean isOrder){
        this.fileoutput=fileoutput;
        this.isOrder = isOrder;
    }
    // Η μέθοδος isTag ελέγχει αν ένα αλφαριθμητικό είναι tag
    private boolean  isTag(String s){
        int i;
        for (i=0;i<tags.length;i++)
            if (s.equalsIgnoreCase(tags[i]))
                return true;
        return false;
    }
    // Η μέθοδος readItem διαβάζει το αρχείο και επιστρέφει true αν
    // διαβάστηκε επιτυχώς, αλλιώς false
    private boolean readItem(){
        String variable, value; // variable είναι το χαρακτηριστικό της συσκευής και value η τιμή αυτού
       /* Ο tokenizer είναι μια κλάση της Java που μας επιτρέπει να διαιρούμε
        * ένα αλφαριθμητικό σε μικρότερα με βάση ειδικούς χαρακτήρες π.χ.
         * κόμμα, τελεία, κενό, tab κτλ
         */
        StringTokenizer st;
        DateFormat df= new SimpleDateFormat("dd/MM/yyyy");
        try {
            // διαβάζουμε μια γραμμή από το αρχείο
            line = fileinput.readLine();  line=line.trim();
            if (line==null) { // αν είναι τέλος αρχείου είναι λάθος
                System.out.println("Unexpected end of file!");
                fileinput.close();
                return false;
            }
            if (line.equalsIgnoreCase("")){
                System.out.println("Empty lines are not allowed!");
                fileinput.close();
                return false;
            }
            /* Αφού διαβάσουμε τη γραμμή τη χωρίζουμε στα αλφαριθμητικά από
             * τα οποία αποτελείται χρησιμοποιώντας τον tokenizer και δίνοντάς
             * του ως παραμέτρους τη γραμμή και τους χαρακτήρες με βάση τους
             * οποίους θα τη σπάσουμε στα επιμέρους αλφαριθμητικά
             */
            st=new StringTokenizer(line," /t");
            line = st.nextToken();  // παίρνουμε το πρώτο αλφαριθμητικό
            /* Η περιγραφή μιας συσκευής ξεκινά με "{" επομένως αν δεν βρούμε
             * αυτό πρώτο έχουμε λάθος προδιαγραφή αρχείου
             */
            if (!(line.equalsIgnoreCase("{"))){
                System.out.println("Invalid file format. { expected!");
                fileinput.close();
                return false;
            }
            /* Οι επόμενες γραμμές του αρχείου θα πρέπει να περιέχουν τα χαρακτηριστικά
             * της συσκευής και τις τιμές τους
             */
             line = fileinput.readLine();line=line.trim();
             if (line.equalsIgnoreCase("")){
                 System.out.println("Empty lines are not allowed!");
                 fileinput.close();
                 return false;
             }
             /* διαβάζουμε γραμμές μέχρι να συναντήσουμε το } που σηματοδοτεί το
              * τέλος της περιγραφής μιας συσκευής
              */
             while (!line.equalsIgnoreCase("}")){ // Μέχρι να φτάσουμε στο τέλος ενός προϊόντος
                 if (line==null) { // αν τελειώσει το αρχείο πριν δούμε το } έχουμε λάθος
                     System.out.println("Unexpected end of file");
                     fileinput.close();
                     return false;
                 }
                 /* Κάθε γραμμή που διαβάζουμε αποτελείται από δύο αλφαριθμητικά:
                  * μια μεταβλητή και την τιμή της. Η χρήση του tokenizer θα μας
                  * επιτρέψει να διαβάσουμε τη μεταβλητή και τη τιμή της με ευκολία
                  */
                 st=new StringTokenizer(line," /t");
                 variable = st.nextToken(); // διαβάζουμε μια μεταβλητή
                 if (isTag(variable)==true){
                    value = st.nextToken(); // και τη τιμή της
                    // Διάβασμα και ανάθεση μεταβλητών
                    if (variable.equalsIgnoreCase("ITEM_TYPE")) ITEM_TYPE=value;
                    if (variable.equalsIgnoreCase("MODEL")) {
                        while (st.hasMoreTokens())
                            value = value +" "+st.nextToken();
                        MODEL=value;
                     }
                    if (variable.equalsIgnoreCase("MANUFACTURER"))  {
                        while (st.hasMoreTokens())
                            value = value +" "+st.nextToken();
                        MANUFACTURER=value;
                     }
                    if (variable.equalsIgnoreCase("PANEL_TYPE")) PANEL_TYPE=value;
                    if (variable.equalsIgnoreCase("DIMENSIONS")) DIMENSION=Double.parseDouble(value);
                    if (variable.equalsIgnoreCase("RESOLUTION")) RESOLUTION=value;
                    if (variable.equalsIgnoreCase("INTERFACES")){
                        while (st.hasMoreTokens())
                            value = value +" "+st.nextToken();
                        INTERFACES=value;
                     }
                    if (variable.equalsIgnoreCase("DVD_FORMAT")) {
                        while (st.hasMoreTokens())
                            value = value +" "+st.nextToken();
                        DVD_FORMAT=value;
                     }
                    if (variable.equalsIgnoreCase("REFRIGERATOR_TYPE")) REFRIGERATOR_TYPE=value;
                    if (variable.equalsIgnoreCase("ENERGY_CLASS")){
                        while (st.hasMoreTokens())
                            value = value +" "+st.nextToken();
                        ENERGY_CLASS=value;
                     }
                    if (variable.equalsIgnoreCase("MODEL_YEAR")) MODEL_YEAR=Integer.parseInt(value);
                    if (variable.equalsIgnoreCase("PIECES")) PIECES=Integer.parseInt(value);
                    if (variable.equalsIgnoreCase("PRICE")) PRICE=Double.parseDouble(value);
                    if (variable.equalsIgnoreCase("CONSERVATION_CAPACITY")) CONSERVATION_CAPACITY=Double.parseDouble(value);
                    if (variable.equalsIgnoreCase("FROST_CAPACITY")) FROST_CAPACITY=Double.parseDouble(value);
                    if (variable.equalsIgnoreCase("WASHING_CAPACITY")) WASHING_CAPACITY=Double.parseDouble(value);
                    if (variable.equalsIgnoreCase("TURNS")) TURNS=Double.parseDouble(value);
                    if (variable.equalsIgnoreCase("CUSTOMER_NAME")){
                        while (st.hasMoreTokens())
                            value = value +" "+st.nextToken();
                        CUSTOMER_NAME=value;
                     }
                    if (variable.equalsIgnoreCase("CUSTOMER_TEL")){
                        while (st.hasMoreTokens())
                            value = value +" "+st.nextToken();
                        CUSTOMER_TEL=value;
                     }
                    if ((variable.equalsIgnoreCase("ORDER_NUMBER")) ||
                       (variable.equalsIgnoreCase("SALE_NUMBER")))
                            ORDERSELL_NUMBER=Integer.parseInt(value);
                    if (variable.equalsIgnoreCase("DATE")) {
                        try {
                            while (st.hasMoreTokens())
                                value = value +"/"+st.nextToken();
                            DATE=df.parse(value);
                        } catch (ParseException e){
                            System.out.println(e.getMessage());
                            DATE=null;
                        }
                    }
                    if (variable.equalsIgnoreCase("DATE_OF_ARRIVAL")) {
                        try {
                            while (st.hasMoreTokens())
                                value = value +"/"+st.nextToken();
                            DATE_OF_ARRIVAL=df.parse(value);
                        } catch (ParseException e){
                            System.out.println(e.getMessage());
                            DATE_OF_ARRIVAL=null;
                        }
                    }
                    line = fileinput.readLine();
                    line=line.trim(); // επόμενη γραμμή
                    if (line.equalsIgnoreCase("")){
                        System.out.println("Empty lines are not allowed!");
                        fileinput.close();
                        return false;
                    }
                 }
                 else{ // αν η μεταβλητή δεν αναγνωρίζεται ως χαρακτηριστικό καμμίας συσκευής
                     System.out.println("Invalid tag '"+variable+"'"); // τότε είναι λάθος το αρχείο
                     fileinput.close();
                     return false;
                }
             }
             /* Ολοκλήρωση διαβάσματος παραγγελίας/πώλησης, επομένως η τελευταία
              * γραμμή πρέπει να έχει το χαρακτήρα }
              */
             st=new StringTokenizer(line," /t");
             line = st.nextToken();
             if (!line.equalsIgnoreCase("}")){ // αν δεν υπάρχει το } έχουμε λάθος
                 System.out.println("Missing } in item specification.");
                 fileinput.close();
                 return false;
             }
             else{
                 return true;}
         }catch (Exception e){
             System.err.println("Error:"+e.getMessage());
             return false;
         }
    }

    public boolean WriteOrderSell(paraggeliapwlisi o, boolean isOrder){
        Suskeui d=o.get_suskeui();
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            fileoutput.write("{");
            fileoutput.newLine();
            if (isOrder)
                fileoutput.write("ORDER_NUMBER "+o.get_kwdikos());
            else
                fileoutput.write("SALE_NUMBER "+o.get_kwdikos());
            fileoutput.newLine();
            fileoutput.write("CUSTOMER_NAME "+o.get_onoma_pelati());
            fileoutput.newLine();
            fileoutput.write("CUSTOMER_TEL "+o.get_tilefwno_pelati());
            fileoutput.newLine();
            fileoutput.write("DATE "+formatter.format(o.get_hmerominia()));
            fileoutput.newLine();
            if (isOrder){
               if (((paraggelia) o).get_expectedDateOfArrival()!=null){
                fileoutput.write("DATE_OF_ARRIVAL "+formatter.format(((paraggelia) o).get_expectedDateOfArrival()));
                fileoutput.newLine();
               }
            }
            ItemParser writedevice=new ItemParser(fileoutput);
            if (!writedevice.writeDevice(d, -1)){
                System.out.println("An error occured while writing an order or sell. Aborting!");
                fileoutput.close();
                return false;
            }
            fileoutput.write("}");fileoutput.newLine();
            return true;
         }catch (Exception e){
             System.err.println("Error:"+e.getMessage());
             return false;
         }
    }

    public paraggeliapwlisi readOrderSell(){
        paraggeliapwlisi os=null; // η συσκευή που διαβάζεται
        Suskeui sys=null;
        if (!readItem()) return null; // αποτυχία διαβάσματος συσκευής
        sys=devices.deviceExists(MODEL, MODEL_YEAR, MANUFACTURER);
        if (sys==null) {
            System.out.println("Unknown sys specification!");
            return null;
        }
        if (isOrder){
            os = new paraggelia(sys,this.CUSTOMER_NAME,this.CUSTOMER_TEL,this.DATE,DATE_OF_ARRIVAL);
            os.set_kostos(PRICE);
        }
        else{
            os = new pwlisi(sys,CUSTOMER_NAME,CUSTOMER_TEL,DATE);
            os.set_kostos(PRICE);
        }
    return os; // επιστροφή της συσκευής που διαβάστηκε ή null αν δεν αναγνωρίζεται ο τύπος της
    }
    // η επόμενη μέθοδος επιστρέφει το αρχείο, το οποίο βρίσκεται στο σημείο πια
    // που ολοκληρώσαμε το διάβασμα μιας συσκευής
    public BufferedReader get_fileinput(){ return fileinput;}
    public BufferedWriter get_fileoutput(){ return fileoutput;}
    public void set_fileoutput(BufferedWriter f) { fileoutput=f;}
}



/* H κλάση SalesOrdersFileParser διαβάζει τα  αρχεία με τις παραγγελίες και
 * τις πωλήσεις. Η κλάση καλεί επαναληπτικά την προηγούμενη κλάση OrderSellParser
 * και κάθε παραγγελία/πώληση που αναγνωρίζεται από την OrderSellParser προστίθεται σε
 * μια εσωτερική λίστα παραγγελιών/πωλήσεων.
 * Ο κατασκευαστής της κλάσης δέχεται σαν όρισμα το όνομα του αρχείου.
 * Η κύρια μέθοδός της είναι η LoadDevicesFromFile() η οποία περιμένει
 * στην πρώτη γραμμή του αρχείο το αναγνωριστικό ITEM_LIST και στην επόμενη
 * το { ακολουθούμενο από μια σειρά περιγραφών συσκευών και στο τέλος το }
 */
class OrdersSalesFileParser <T extends paraggeliapwlisi>{
    private diathesimes_suskeues devices; // Η λίστα διαθέσιμων συσκευών διατίθενται από το αρχείο
    private String filename; // το όνομα του αρχείου
    private List<T> os; // η προσωρινή λίστα παραγγελιών/πωλήσεων
    // Ο κατασκευαστής της κλάσης
    // δέχεται σαν παραμέτρους το όνομα του αρχείου και τη λίστα των
    // διαθέσιμων συσκευών
    public OrdersSalesFileParser(String fname, diathesimes_suskeues devices){
        filename = fname; // αρχικοποίηση των μεταβλητών της κλάσης
        this.devices = devices;
        os = new ArrayList<T>();
    }
    // O κατασκευαστής της κλάσης για εγγραφή παραγγελιών/πωλήσεων σε αρχείο
    public OrdersSalesFileParser(String fname, List <T> os){
        this.os=os;
        this.filename=fname;
    }
    // Η κύρια μέθοδος της κλάσης που διαβάζει τις παραγγελίες/πωλήσεις από το αρχείο
    // και τις αποθηκεύει στην εσωτερική λίστα των διαθέσιμων συσκευών
    public List<T> LoadOrdersSellsFromFile(){
        // κώδικας ελέγχου ύπαρξης αρχείου
        File f=new File(filename);
        if (!f.exists()){
            System.out.println("File not found!");
            return null;
        }
        OrderSellParser parser; // χρησιμοποιείται για το διάβασμα μιας συσκευής
        int quantity; // η διαθέσιμη ποσότητα μιας συσκευής
        String tag;
        Boolean isOrder=false;
        T o=null;
        // κώδικας για άνοιγμα αρχείου προς ανάγνωση
        try {
            // ανοίγουμε ένα αρχείο fileinput για ανάγνωσή του
            BufferedReader fileinput = new BufferedReader(new FileReader(filename));
            String line=fileinput.readLine(); // διαβάζω γραμμή από το αρχείο
            line=line.trim(); // απορρίπτω τα κενά πριν και μετά τη γραμμή
            // έλεγχος αν η πρώτη γραμμή του αρχείου είναι το ORDER_LIST ή το SALES_LIST
            if (line.equalsIgnoreCase("ORDER_LIST")) isOrder=true;
            if (line.equalsIgnoreCase("SALES_LIST")) isOrder=false;
            if ((!(line.equalsIgnoreCase("ORDER_LIST"))) &&
                (!(line.equalsIgnoreCase("SALES_LIST")))) { // αν όχι το αρχείο δεν είναι σωστό
                System.out.println("Invalid file format. Not found ORDER_LIST or SALES_LIST tag");
                fileinput.close(); // κλείσιμο αρχείου
                return null;
            }
            StringTokenizer st = new StringTokenizer(line," /t");
            line=fileinput.readLine();line=line.trim();
            // Η επόμενη γραμμή πρέπει να περιέχει το { που σηματοδοτεί την έναρξη
            // μιας λίστας συσκευών
            if (!line.equalsIgnoreCase("{")) { // αλλιώς είναι λάθος
                if (isOrder) System.out.println("{ expected as beginnning of ORDER_LIST");
                else System.out.println("{ expected as beginnning of SALES_LIST");
                fileinput.close();
                return null;
            }
            line=fileinput.readLine();
            if (line==null) {
                System.out.println("Unexpected end of list!");
                fileinput.close();
                return null;
            }
            // Αν η γραμμή δεν είναι κενή τότε ξεκινούμε να διαβάζουμε τη λίστα
            // μέχρι να βρούμε το }
            line=line.trim();
            if (line.equalsIgnoreCase("")){
                System.out.println("Empty lines are not allowed!");
                fileinput.close();
                return null;
            }
            st = new StringTokenizer(line," /t");
            while (!st.nextToken().equalsIgnoreCase("}")){
                st = new StringTokenizer(line," /t");
                // Αν η γραμμή που διαβάστηκε έχει το tag ORDER ή SALE
                // τότε πρόκειται για νέα παραγγελία/πώληση και θα τη διαβάσουμε
                // με το OrderSellParser
                tag=st.nextToken();
                if ((isOrder && tag.equalsIgnoreCase("ORDER")) ||
                   ((!isOrder) && tag.equalsIgnoreCase("SALE"))){
                    parser = new OrderSellParser(fileinput,isOrder,devices); // διάβασμα πώλησης/παραγγελίας
                    o=(T) parser.readOrderSell();// δημιουργία αντικειμένου
                    if (o!=null) // αν η παραγγελία/πώληση συσκευή δημιουργήθηκε με επιτυχία
                        this.os.add(o);
                    else return null; // τερματισμός διαβάσματος λόγω λάθους στην περιγραφή της πώλησης/παραγγελίας
                    fileinput = parser.get_fileinput(); // ενημέρωση του δείκτη του αρχείου στη γραμμή που είμαστε μετά το διάβασμα της πώλησης/παραγγελίας
                    line=fileinput.readLine();
                    line=line.trim();
                    if (line.equalsIgnoreCase("")){
                        System.out.println("Empty lines are not allowed!");
                        fileinput.close();
                        return null;
                    }
                    st = new StringTokenizer(line," /t");
                    if (line==null){  // αν όμως φτάσουμε στο τέλος χωρίς να βρούμε το }
                        System.out.append("Unexpected End of File!!");
                        fileinput.close();
                        return null;
                    }
                }
                else { // αν δεν βρεθεί το ORDER/SELL
                       if (!tag.equalsIgnoreCase("}")){
                        if (isOrder) System.out.println(" ORDER expected but was not found!");
                        if (!isOrder) System.out.println(" SALE expected but was not found!");
                        fileinput.close();
                         return null;
                    }
                }
            }

            fileinput.close();
            return os;
        }catch (Exception e){
            System.err.println("Error:"+e.getMessage());
            return null;
        }
    }

   // H μέθοδος της κλάσης που αποθηκεύει μια παραγγελία ή πώληση σε αρχείο
   public boolean WriteOrdersSellsToFile(boolean isOrderList){
       T o=null;
       int i;
       try{


          // ανοίγουμε ένα αρχείο fileinput για ανάγνωσή του
          BufferedWriter fileoutput = new BufferedWriter(new FileWriter(filename));
           if (isOrderList) fileoutput.write("ORDER_LIST");
           else fileoutput.write("SALES_LIST");
           fileoutput.newLine();fileoutput.write("{");fileoutput.newLine();

           OrderSellParser orderWriter= new OrderSellParser(fileoutput,isOrderList);
           for(i=0;i<this.os.size();i++){
               if (isOrderList) fileoutput.write("ORDER");
               else fileoutput.write("SALE");
               fileoutput.newLine();
               o = os.get(i);
               orderWriter.set_fileoutput(fileoutput);
               if (!orderWriter.WriteOrderSell(o, isOrderList)){
                   System.out.println("An error has occured while writing order/sell list.Aborting!");
                   fileoutput.close();
                   return false;
               }
               fileoutput=orderWriter.get_fileoutput();

           }


       fileoutput.write("}");
       fileoutput.close();

       return true;

       }catch (Exception e){
            System.err.println("Error:"+e.getMessage());
            return false;
        }


   }
}
class Store {
    private diathesimes_suskeues diathesimes_suskeues;
    private List <paraggelia> paraggeltheises_suskeues;
    private List <pwlisi> politheises_suskeues; // κατάλογος πωλήσεων
    private String storeName; // όνομα μαγαζιού
    private String availDevFilename; // όνομα αρχείου διαθέσιμων συσκευών
    private String ordersFilename; // όνομα αρχείου παραγγελιών
    private String salesFilename; // όνομα αρχείου πωλήσεων
    // Κατασκευαστής που δέχεται σαν παράμετρο το όνομα του μαγαζιού
    public Store (String name){
        storeName = name;
        diathesimes_suskeues = new diathesimes_suskeues();
        paraggeltheises_suskeues = new ArrayList <paraggelia> ();
        politheises_suskeues = new ArrayList <pwlisi> ();
        String userDir = System.getProperty("user.dir")+"\\";
        availDevFilename =userDir+"availDev.txt";
        ordersFilename = userDir+"orders.txt";
        salesFilename = userDir+"sales.txt";

    }
    public void addparaggelia(Suskeui Suskeui, String custName, String custTel, Date hmerominia,Date expectedDate  ){
        paraggeltheises_suskeues.add(new paraggelia (Suskeui,  custName,  custTel,  hmerominia, expectedDate));
    }
    public void addpwlisi(Suskeui Suskeui, String custName, String custTel, Date hmerominia){
        politheises_suskeues.add(new pwlisi(Suskeui, custName, custTel,hmerominia));
    }
    public void add_Diathesimi_suskeui(Suskeui Suskeui, int posotita){
        diathesimes_suskeues.addNewsuskeui(Suskeui, posotita);
    }
    public String get_storeName(){ return storeName;}
    public void displayAvailabesuskeuis(){
        System.out.println(diathesimes_suskeues);
    }
    public void displaypwlisis(){
        int i;
        for (i=0;i<politheises_suskeues.size();i++)
            System.out.println(politheises_suskeues.get(i));
    }
    public void displayparaggelias(){
        int i;
        for (i=0;i<paraggeltheises_suskeues.size();i++){
                System.out.println(paraggeltheises_suskeues.get(i));
        }
    }
    public int get_diathesimes_suskeuesCount(){ return diathesimes_suskeues.metritis();}
    public Suskeui get_Availablesuskeui(int i) { return diathesimes_suskeues.getsuskeui(i);}
    public int get_Availableposotita(Suskeui d) { return diathesimes_suskeues.getsuskeuiposotita(d);}
    public void set_Availableposotita(Suskeui d, int q) {diathesimes_suskeues.setsuskeuiposotita(d, q);
    }

    public void Receiveparaggelia(int i){
        paraggelia o;
        int posotita;
        Date d;
        o = paraggeltheises_suskeues.get(i);
        if ((o!=null) ){
            o.set_orderStatus("FINISHED");
            posotita = diathesimes_suskeues.getsuskeuiposotita(o.get_suskeui());
            diathesimes_suskeues.setsuskeuiposotita(o.get_suskeui(), posotita+1);
            paraggeltheises_suskeues.remove(o);
            d=new Date(); d.getTime();
            politheises_suskeues.add(new pwlisi(o.get_suskeui(),o.get_onoma_pelati(),o.get_tilefwno_pelati(),d));
        }
    }
    public int getparaggeliasCount(){ return paraggeltheises_suskeues.size();}
    public int getSalesCount(){ return politheises_suskeues.size();}
    public int getparaggeliaIndexFromCode(int kwdikos){
        int i;
        for (i=0;i<paraggeltheises_suskeues.size();i++)
            if (paraggeltheises_suskeues.get(i).get_kwdikos()==kwdikos)
                return i;
        return -1;
    }
    public paraggelia getParaggelia(int i){
        return this.paraggeltheises_suskeues.get(i);
    }
    // Ανάκτηση Πώλησης με βάση τη θέση της στη λίστα πωλήσεων
    public pwlisi getPwlisi(int i){
        return this.politheises_suskeues.get(i);
    }
    public int getFirstparaggeliaCode(){ return paraggeltheises_suskeues.get(0).get_kwdikos();}
    public int getLastparaggeliaCode() { return paraggeltheises_suskeues.get(paraggeltheises_suskeues.size()-1).get_kwdikos();}
    public int LoadAvailableDevicesFromFile(String filename){
        diathesimes_suskeues loadedDevices;
        AvailableDevicesFileParser fileParser;
        int i,quantity;
        Suskeui dev;
        if (filename!=null) this.availDevFilename=filename;
        fileParser=new AvailableDevicesFileParser(availDevFilename);
        loadedDevices = fileParser.LoadDevicesFromFile();
        if (loadedDevices!=null){
            for(i=0;i<loadedDevices.metritis();i++){
                dev = loadedDevices.get_Suskeui(i);
                quantity = loadedDevices.getsuskeuiposotita(dev);
                add_Diathesimi_suskeui(dev, quantity);
            }
            return 1;
        }
        else
            return 0;
    }
    // Εγγραφή διαθέσιμων συσκευών σε αρχείο
    public boolean WriteAvailableDevicesToFile(String filename){
        AvailableDevicesFileParser fileParser;
        if (filename!=null)
            availDevFilename = filename;
        fileParser=new AvailableDevicesFileParser(availDevFilename,this.diathesimes_suskeues);
        return fileParser.WriteDevicesToFile();
    }
    // Aνάγνωση αρχείου παραγγελιών μέσω της κλάσης OrdersSalesFileParser
    public int LoadOrdersSalesFromFile(boolean isOrder,String filename){
        List<paraggelia> o=new ArrayList();
        List<pwlisi> s=new ArrayList();
        OrdersSalesFileParser fileParser;
        int i;
        if (filename!=null) this.ordersFilename=filename;
        if (isOrder) {
            fileParser=new OrdersSalesFileParser(this.ordersFilename, diathesimes_suskeues);
            o = (List <paraggelia>) fileParser.LoadOrdersSellsFromFile();
            if (o!=null)
                for(i=0;i<o.size();i++) this.paraggeltheises_suskeues.add(o.get(i));
            else return 0;
        }
        else{
            fileParser=new OrdersSalesFileParser(this.salesFilename, diathesimes_suskeues);
            s = (List <pwlisi>) fileParser.LoadOrdersSellsFromFile();
            if (s!=null)
                for(i=0;i<s.size();i++) this.politheises_suskeues.add(s.get(i));
            else return 0;
        }
        return 1;
    }
    // Εγγραφή των πωλήσεων/παραγγελιών
    public boolean WriteOrdersSalesToFile(boolean isOrder, String filename){
       OrdersSalesFileParser <paraggelia> fileparserorders;
       OrdersSalesFileParser <pwlisi> fileparsersales;
       if (filename!=null) this.ordersFilename=filename;
        if (isOrder){
            fileparserorders=new  OrdersSalesFileParser<paraggelia>(this.ordersFilename,this.paraggeltheises_suskeues);
            return fileparserorders.WriteOrdersSellsToFile(isOrder);
        }
        else{
            fileparsersales = new OrdersSalesFileParser<pwlisi>(this.salesFilename,this.politheises_suskeues);
            return fileparsersales.WriteOrdersSellsToFile(isOrder);
        }
    }
}
