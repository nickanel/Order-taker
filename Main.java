

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import java.text.*;
class DeviceInfoBox extends JDialog implements ActionListener{
   private JFrame parent;
   private JTextArea  info;
   private JLabel image; 
   private JButton closeButton, sellButton; 
   private String title;
   public DeviceInfoBox(JFrame parent) {
        super();
        this.parent = parent;
        initComponents();
        this.setBounds(400, 800, 1000, 500);
    }
   // Τοποθέτηση πληροφορίας στο JTextArea και της εικόνας στο JLabel
   public void setInformation(){
       String imageicon=null; // αρχικά δεν υπάρχει εικόνα συσκευής
       Suskeui d;
       d=((Application) parent).getSelectedDevice();
       if (d instanceof TV) imageicon="tv.jpg";
       if (d instanceof DVD) imageicon = "dvd.jpg";
       if (d instanceof Pluntirio) imageicon = "washer.jpg";
       if (d instanceof Psugeio) imageicon = "refrigerator.jpg";
       this.title = d.get_onoma_modelou();
       this.setTitle(title);
       info.setText(d.toString());
       image.setIcon(new ImageIcon("Images/"+imageicon));
   }
   
   // Τοποθέτηση των συστατικών στο πλαίσιο διαλόγου 
    private void initComponents() {
        closeButton = new JButton(); // κουμπί εξόδου
        sellButton = new JButton(); // κουμπί πώλησης
        info = new JTextArea(); // κείμενο πληροφοριών
        image = new JLabel(); // εικόνα συσκευής
        JPanel panel1 = new JPanel(); // πανελ που θα τοποθετήσουμε μέσα του τα κουμπιά
        info.setSize(500,500);  // μέγεθος κειμένου πληροφοριών
        info.setLineWrap(true); // αναδίπλωση λέξεων στο JTextArea
        info.setBackground(Color.yellow); // χρώμα φόντου στο JTextArea
        add(info, BorderLayout.NORTH);
        panel1.setSize(300, 400);
        add(panel1,BorderLayout.SOUTH);
        image.setSize(150, 150); // μέγεθος εικόνας
        image.setHorizontalAlignment(JLabel.CENTER); // η εικόνα τοποθετείται στο κέντρο του πλαισίου διαλόγου
        add(image,BorderLayout.CENTER); // τοποθέτηση εικόνας στο κέντρο του πλαίσιου διαλόγου
        closeButton.setText("'Έξοδος"); // κείμενο που θα εμφανίζεται στο κουμπί κλεισίματος
        closeButton.setSize(100, 100); // μέγεθος του κουμπιού κλεισίματος
        closeButton.addActionListener(this); // ανατιθεται ο actionlistener που υλοποιεί η κλάση για να μπορέσουμε να κάνουμε ενέργειες με το κουμπί αυτό
        sellButton.setText("Πώληση"); // κείμενο που θα εμφανίζεται στο κουμπί πώλησης
        sellButton.setSize(400, 400); // μέγεθος του κουμπιού πώλησης
        sellButton.addActionListener(this); // ανατιθεται ο actionlistener που υλοποιεί η κλάση για να μπορέσουμε να κάνουμε ενέργειες με το κουμπί αυτό
        panel1.add(closeButton,BorderLayout.WEST);
        panel1.add(sellButton,BorderLayout.EAST);
        repaint(); // ζωγραφίζουμε το πλαίσιο διαλόγου
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true); //μόνο κλείσιμo του παραθύρου
        setResizable(true);
        pack(); // συμπυκνώνουμε τον ελεύθερο χώρο μεταξύ των συστατικών
    }

    public void actionPerformed(ActionEvent e){
            if(e.getSource()==closeButton){ // αν το γεγονός είναι κλικ στο κουμπί closeButton
                setVisible(false);
            }
            if (e.getSource()==this.sellButton){ // αν το γεγονός είναι κλικ στο κουμπί πώλησης
                ( (Application) parent).Sell(); // κάλεστε τη μέθοδο sell του πατέρα
                setVisible(false); // κρύψε το πλαίσιο για να μην φαίνεται άλλο
            }
    }
}

/*
 * H παρακάτω κλάση υλοποιεί την εφαρμογή μας.
 */

class Application extends JFrame implements ActionListener, MouseListener{
    private Store myStore; // Το κατάστημά μας
    private Suskeui selectedDevice; // Επιλεγμένη συσκευή από τον πίνακα των διαθέσιμων συσκευών
    private JMenu menu1, menu2; // μενου επιλογών
    private JMenuItem jmLoadAvailableDevices, // επιλογή μενού  φόρτωσης διαθέσιμων συσκευών
                      jmLoadOrdersList, // επιλογή μενού φόρτωσης παραγγελιών
                      jmLoadSalesList, // επιλογή μενού φόρτωσης πωλήσεων
                      jmSaveAvailableDevices, // επιλογή μενού αποθήκευσης διαθέσιμων συσκευών
                      jmSaveOrdersList, // επιλογή μενού αποθήκευσηςπαραγγελιών
                      jmSaveSalesList, // επιλογή μενού αποθήκευσης πωλήσεων
                      jmExit, // επιλογή μενού εξόδου από την εφαρμογή
                      jmAbout; // επιλογή πληρφοριών σχετικά με την εφαρμογή
    private JMenuBar jmMenuBar; // τοποθέτησης της μπάρας μενού
    private JTabbedPane tabs; //εμφάνιση καρτελών στο γραφικό περιβάλλον της εφαρμογής
    private JTable  jTableAvailableDevices,  // στοιχείο (component) με τη λίστα των διαθέσιμων συσκευών
                    jTableSales, // component με τη λίστα των πωλήσεων
                    jTableOrders; // component με τη λίστα των παραγγελιών
    private DefaultTableModel  jTableAvailableDevicesModel,
                               jTableSalesModel,
                               jTableOrdersModel; //  DefaultTableModel
    private DeviceInfoBox deviceInfoBox; // το πλαίσιο διαλόγου, η κλάση του οποίου ορίστηκε προηγουμένως, για την εμφάνιση πληροφοριών της συσκευής
    private JToolBar toolbar;
    private JButton tbuttonSell,tbuttonOrder,tbuttonOrderSell;
    private JLabel statusbar;
    public Store getStore(){return myStore;}
    public Suskeui getSelectedDevice(){return selectedDevice;}
    public JTable getAvailableDevicesTable(){ return jTableAvailableDevices;} 
    private void DisplayDevicesinTable(){
        int i;
        // Διαγραφή των υπαρχουσών γραμμών
        while (jTableAvailableDevicesModel.getRowCount()>0){
            jTableAvailableDevicesModel.removeRow(0);
        }
        // προσθήκη των νέων γραμμών που προκύπτουν από τις διαθέσιμες συσκευές
        for (i=0;i<myStore.get_diathesimes_suskeuesCount();i++)
            jTableAvailableDevicesModel.addRow(new Object[]{ // κάθε γραμμή είναι ένα αντικείμενο στηλών
            myStore.get_Availablesuskeui(i).get_onoma_modelou(), // οπότε πρέπει να ορίσουμε τι είναι τα περιεχόμενα
            myStore.get_Availablesuskeui(i).get_kataskeuastis(), // κάθε στήλης
            myStore.get_Availablesuskeui(i).get_etos(), // οι στήλες ορίζονται κατά τη δημιουργία
            myStore.get_Availablesuskeui(i).get_timi(), // του tablemodel που γίνεται σε επόμενη μέθοδο
            myStore.get_Availableposotita(myStore.get_Availablesuskeui(i))});
       repaint();

    }
    private void DisplaySellsinTable(){
        int i;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        // Φόρτωση των στοιχείων των διαθέσιμων πωλήσεων
            // διαγραφή των υπαρχουσών γραμμών
        while (jTableSalesModel.getRowCount()>0){
            jTableSalesModel.removeRow(0);
        }
            // προσθήκη των νέων γραμμών
        for (i=0;i<myStore.getSalesCount();i++)
            jTableSalesModel.addRow(new Object[]{
                myStore.getPwlisi(i).get_kwdikos(),
                myStore.getPwlisi(i).get_suskeui().get_onoma_modelou(),
                myStore.getPwlisi(i).get_onoma_pelati(),
                myStore.getPwlisi(i).get_tilefwno_pelati(),
       formatter.format(myStore.getPwlisi(i).get_hmerominia())});
        repaint();
    }

    /*
     * Η παρακάτω μέθοδος τοποθετεί γραμμές στο JTable για την εμφάνιση
     * της λίστας των παραγγελιών που έχουν πραγματοποιηθεί
     * χρησιμοποιώντας το αντίστοιχο DefautlTableModel
     */
    
    private void DisplayOrdersinTable(){
        int i;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        while (jTableOrdersModel.getRowCount()>0){
            jTableOrdersModel.removeRow(0);
        }
        // προσθήκη των νέων γραμμών
        for (i=0;i<myStore.get_diathesimes_suskeuesCount();i++)
                jTableOrdersModel.addRow(new Object[]{
                                            myStore.getParaggelia(i).get_kwdikos(),
                                            myStore.getParaggelia(i).get_suskeui().get_onoma_modelou(),
                                            myStore.getParaggelia(i).get_onoma_pelati(),
                                            myStore.getParaggelia(i).get_tilefwno_pelati(),
                                            formatter.format(myStore.getParaggelia(i).get_hmerominia()),
                                            formatter.format(myStore.getParaggelia(i).get_expectedDateOfArrival())
                });
       repaint();       
    }
    // 1. Παραγγελία μιας συσκευής
    public double Order(){
        Date dateOfOrder = new Date(); // Η σημερινή ημερομηνία
        Date expectedDateofArrival; // Η αναμενόμενη ημερομηνία παραλαβής της συσκευής
        double finalcost=0; // τελικό κόστος παραγγελίας
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String custName = JOptionPane.showInputDialog("ΔΩΣΕ ΤΟ ΟΝΟΜΑ ΤΟΥ ΠΕΛΑΤΗ:"); // εμφάνιση πλαίσιου διαλόγου που ζητά το όνομα του πελάτη
        String custTel = JOptionPane.showInputDialog("ΔΩΣΕ ΤΟ ΤΗΛΕΦΩΝΟ ΤΟΥ ΠΕΛΑΤΗ:"); // εμφάνιση πλαισίου διαλόγου που ζητά το τηλέφωνο του πελάτη
        String dateofarrival = JOptionPane.showInputDialog("ΗΜΕΡΟΜΗΝΙΑ ΠΑΡΑΛΑΒΗΣ (dd/mm/yyyy):"); // εμφάνιση πλαισίου διαλόγου που ζητά την αναμενόμενη ημερομήνία παραλαβής
        dateOfOrder.getTime();
        if (this.jTableAvailableDevices.getSelectedRow()!=-1){ // αν ο χρήστης έχει επιλέξει μια γραμμή με το ποντίκι στον πίνακα με τις διαθέσιμες συσκευές
            selectedDevice=myStore.get_Availablesuskeui(jTableAvailableDevices.getSelectedRow()); // λαμβάνουμε ποια είναι η συσκευή αυτή
            if (selectedDevice instanceof TV) finalcost = ( (TV) selectedDevice).get_timi(); //ανάλογα με τη συσκευή καλούμε και την αντίστοιχη μέθοδό της για
            if (selectedDevice instanceof DVD) finalcost = ( (DVD) selectedDevice).get_timi(); // υπολογισμό της τιμής της με την έκτπωση
            if (selectedDevice instanceof Psugeio) finalcost = ( (Psugeio) selectedDevice).get_timi();
            if (selectedDevice instanceof Pluntirio) finalcost = ( (Pluntirio) selectedDevice).get_timi();
        }
        else{ // αλλιώς αν ο χρήστης δεν έχει επιλέξει συσκευή εμφανίζουμε κατάλληλο μήνυμα λάθους
            JOptionPane.showMessageDialog(null, "ΔΕΝ ΕΠΙΛΕΧΘΗΚΕ ΚΑΜΙΑ ΣΥΣΚΕΥΗ!", myStore.get_storeName(), JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        try {expectedDateofArrival=df.parse(dateofarrival);} catch (ParseException e){ // μετατροπή ημερομηνίας από string σε αντικείμενο τύπου Date
            System.out.println(e.getMessage());expectedDateofArrival=null;
        }
        if ( (custName!=null) && (custTel!=null) && (dateOfOrder!=null) && (expectedDateofArrival!=null)){  // αν ο χρήστης στα προηγούμενα πλαίσια δεν έχει πατήσει πουθενά cancel
            myStore.addparaggelia(selectedDevice, custName, custTel,dateOfOrder,expectedDateofArrival); // τοποθετούμε την παραγγελία και εμφανίζουμε κατάλληλο μήνυμα
            JOptionPane.showMessageDialog(null, "Η ΠΑΡΑΓΓΕΛΙΑ ΤΗΣ ΣΥΣΚΕΥΗΣ ΚΑΤΑΧΩΡΗΘΗΚΕ!\nΤΟ ΤΕΛΙΚΟ ΚΟΣΤΟΣ ΕΙΝΑΙ: "+finalcost, myStore.get_storeName(), JOptionPane.INFORMATION_MESSAGE);
        }
        else // αλλιώς εμφανίζουμε μήνυμα ότι ο χρήστης ακύρωσε τη διαδικασία της παραγγελίας
          JOptionPane.showMessageDialog(null, "Η ΠΑΡΑΓΓΕΛΙΑ ΑΚΥΡΩΘΗΚΕ!", myStore.get_storeName(), JOptionPane.ERROR_MESSAGE);
        DisplayDevicesinTable(); // ενημερώνουμε τους πίνακες συσκευών
        DisplayOrdersinTable(); // παραγγελιών
        DisplaySellsinTable(); // πωλήσεων
        return finalcost; // επιστρέφουμε το τελικό κόστος παραγγελίας
    }
    
    // 2. Πώληση συσκευής και παραγγελία αυτής αν δεν είναι διαθέσιμη
    public void Sell(){
        double finalcost=0; // κόστος πώλησης
        Date date = new Date();    // ημερομηνία πώλσησης
        if (this.jTableAvailableDevices.getSelectedRow()!=-1){ // αν ο χρήστης έχει επιλέξει συσκευή με το ποντίκι του από τον πίνακα των διαθέσιμων συσκευών
            selectedDevice=myStore.get_Availablesuskeui(jTableAvailableDevices.getSelectedRow());// βρίσκουμε το αντικείμενο της συσκευής
            if (selectedDevice instanceof TV) finalcost = ( (TV) selectedDevice).get_timi(); // και ανάλογα με την κλάση της
            if (selectedDevice instanceof DVD) finalcost = ( (DVD) selectedDevice).get_timi(); // υπολογίζουμε την τελική της τιμή
            if (selectedDevice instanceof Psugeio) finalcost = ( (Psugeio) selectedDevice).get_timi(); // συμπεριλαμβανομένου και της έκπτωσης
            if (selectedDevice instanceof Pluntirio) finalcost = ( (Pluntirio) selectedDevice).get_timi();
            if (myStore.get_Availableposotita(selectedDevice)>0){ // αν η συσκευή είναι διαθέσιμη
                String custName = JOptionPane.showInputDialog(null, "ΔΩΣΕ ΤΟ ΟΝΟΜΑ ΤΟΥ ΠΕΛΑΤΗ:", myStore.get_storeName()); // ζητάμε διαδοχικά το όνομα του πελάτη
                String custTel = JOptionPane.showInputDialog("ΔΩΣΕ ΤΟ ΤΗΛΕΦΩΝΟ ΤΟΥ ΠΕΛΑΤΗ:"); // και το τηλέφωνό του με κατάλληλα πλαίσια διαλόγου
                if ((custName!=null) && (custTel!=null)){ // αν ο χρήστης δεν έχει ακυρώσει κάποιο από αυτά
                    myStore.addpwlisi(selectedDevice, custName, custTel, date); // τοποθετοούμε την πώληση και εμφανίζουμε και κατάλληλο μήνυμα
                    JOptionPane.showMessageDialog(null, "Η ΠΑΡΑΓΓΕΛΙΑ ΤΗΣ ΣΥΣΚΕΥΗΣ ΚΑΤΑΧΩΡΗΘΗΚΕ!\nΤΟ ΤΕΛΙΚΟ ΚΟΣΤΟΣ ΕΙΝΑΙ: "+finalcost, myStore.get_storeName(), JOptionPane.INFORMATION_MESSAGE);
                    DisplaySellsinTable(); // εμφανίζουμε ξανά τον πίνακα των πωλήσεων
                    DisplayDevicesinTable(); // και τον πίνακα των διαθέσιμων συσκευών
                } // αλλίως αν ο χρήστης ακύρωσε τη διαδικασία της πώλησης επιστρέφουμε κατάλληλο μήνυμα
                else JOptionPane.showMessageDialog(null, "Η ΠΑΡΑΓΓΕΛΙΑ ΑΚΥΡΩΘΗΚΕ!", myStore.get_storeName(), JOptionPane.ERROR_MESSAGE);
            }     
        else{ // αλλίως αν η ποσότητα της συσκευής είναι 0 εμφανίζουμε κατάλληλο μήνυμα
            JOptionPane.showMessageDialog(null, "ΔΕΝ ΥΠΑΡΧΕΙ Η ΣΥΣΚΕΥΗ!", myStore.get_storeName(), JOptionPane.ERROR_MESSAGE);
            if (JOptionPane.showConfirmDialog(null, "ΔΗΜΙΟΥΡΓΙΑ ΝΕΑΣ ΠΑΡΑΓΓΕΛΙΑΣ?", myStore.get_storeName(), JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            Order(); // αν ο χρήστης θελήσει παραγγελία τότε προχωράμε στην παραγγελία
            DisplayOrdersinTable();
            DisplayDevicesinTable();
        }
     }
   }
   else    // αλλιώς εμφανίζουμε κατάλληλο μήνυμα μη επιλογής συσκευής
     JOptionPane.showMessageDialog(null, "ΔΕΝ ΕΠΙΛΕΧΘΗΚΕ ΚΑΜΙΑ ΣΥΣΚΕΥΗ!!", myStore.get_storeName(), JOptionPane.ERROR_MESSAGE);
          
 }
   
    // 3. Φόρτωση διαθέσιμων συσκευών από αρχείο
   public void LoadAvailableDevices(){
        String filename;
        String userDir = System.getProperty("user.dir"); // To directory της εφαρμογής
        JFileChooser choosefile = new JFileChooser(userDir); // επιτρέπει την επιλογή αρχείου για άνοιγμα
        filename=null;
        if (choosefile.showOpenDialog(menu1)==JFileChooser.APPROVE_OPTION)  // εμφανίζεται το πλαίσιο διαλόγου για επιλογή αρχείου
                filename = choosefile.getSelectedFile().getAbsolutePath(); // και λαμβάνουμε στη μεταβλητή filename το αρχείο που επέλεξε ο χρήστης
        if (myStore.LoadAvailableDevicesFromFile(filename)==0){ // καλούμε την αντίστοιχη μέθοδο φόρτωσης από αρχείο του αντικειμένου myStore
            JOptionPane.showMessageDialog(null, "ΔΕΝ ΒΡΕΘΗΚΕ ΤΟ ΑΡΧΕΙΟ!", // σε περίπτωση μη εύρεσης του αρχείου
            myStore.get_storeName(), JOptionPane.ERROR_MESSAGE);                                        
            JOptionPane.showMessageDialog(null,"ΔΙΝΟΝΤΑΙ ΠΡΟΚΑΘΟΡΙΣΜΕΝΕΣ ΤΙΜΕΣ ΓΙΑ ΤΗΝ ΧΡΗΣΗ ΤΗΣ ΕΦΑΡΜΟΓΗΣ\n"
                                                + "ΔΗΜΙΟΥΡΓΙΑ ΓΙΑ ΠΡΩΤΗ ΦΟΡΑ ΤΟΥ ΑΡΧΕΙΟΥ!",    // αρχικοποιούμε τη λίστα των διαθέσιμων συσκευών με
            myStore.get_storeName(), JOptionPane.INFORMATION_MESSAGE);                          // μερικές demo τιμές
            // Αρχικοποίηση διαθέσιμων συσκευών σε περίπτωση που το αρχείο δεν υπάρχει
            myStore.add_Diathesimi_suskeui(new TV("BRAVIA",2008,"SONY","ATTD",32,"1024x768","HDMI,DVI",1500), 10);
            myStore.add_Diathesimi_suskeui(new TV("INSPERIA",2009,"PHILIPS","Plasma",42,"2024x1768","HDMI,DVI",3500), 5);
            myStore.add_Diathesimi_suskeui(new TV("FX-100",2008,"LG","LCD",18,"1024x768","COMPOSITE",200), 15);
            myStore.add_Diathesimi_suskeui(new DVD("M56",2010,"LG","AT-R","2048*1024",150), 10);
            myStore.add_Diathesimi_suskeui(new DVD("INSPERIUM",2010,"PHILIPS","BD-RD","2048*1024",250), 20);
            myStore.add_Diathesimi_suskeui(new DVD("BRAVIUM",2009,"SONY","DVD+RW","2048*1024",650), 20);
            myStore.add_Diathesimi_suskeui(new Psugeio("K100",2009,"LG","Closet",100,30,"A+",250), 20);
            myStore.add_Diathesimi_suskeui(new Psugeio("SPARTA",2010,"LG","Closet",600,30,"A+",800), 10);
            myStore.add_Diathesimi_suskeui(new Psugeio("C10",2008,"PITSOS","Mono",70,10,"A+",800), 10);
            myStore.add_Diathesimi_suskeui(new Pluntirio("HLIS",2010,"PHILIPS",6,1000,"A+",500), 10);
            myStore.add_Diathesimi_suskeui(new Pluntirio("FX15",2010,"SAMSUNG",5,600,"A",300), 20);
            myStore.add_Diathesimi_suskeui(new Pluntirio("KTR",2010,"PITSOS",6,1000,"A+",450), 10);
            JOptionPane.showMessageDialog(null, "ΟΙ ΠΡΟΚΑΘΟΡΙΜΕΝΕΣ ΤΙΜΕΣ ΦΟΡΤΩΘΗΚΑΝ ΕΠΙΤΥΓΧΩΣ!",
                            myStore.get_storeName(), JOptionPane.INFORMATION_MESSAGE);
        }
        else JOptionPane.showMessageDialog(null,"ΟΙ ΠΡΟΚΑΘΟΡΙΜΕΝΕΣ ΤΙΜΕΣ ΦΟΡΤΩΘΗΚΑΝ ΕΠΙΤΥΓΧΩΣ!",
                            myStore.get_storeName(), JOptionPane.INFORMATION_MESSAGE);
        DisplayDevicesinTable(); // εμφανίζουμε τις διαθέσιμες συσκευές στον πίνακα των διαθέσιμων συσκευών
   }
    
   // 4. Φόρτωση πωλήσεων από αρχείο
      public void LoadSales(){
        int i; String filename;
        String userDir = System.getProperty("user.dir"); // To directory της εφαρμογής
        JFileChooser choosefile = new JFileChooser(userDir); // επιτρέπει την επιλογή αρχείου για άνοιγμα
        filename=null; // Η λειτουργία είναι παρόμοια με την προηγούμενη μέθοδο
       if (choosefile.showOpenDialog(menu1)==JFileChooser.APPROVE_OPTION)
                filename = choosefile.getSelectedFile().getAbsolutePath();
        if (myStore.LoadOrdersSalesFromFile(false, filename)==0){ // καλούμε την αντίστοιχει μέθοδο του αντικειμένου Store
            JOptionPane.showMessageDialog(null, "ΔΕΝ ΒΡΕΘΗΚΕ ΤΟ ΑΡΧΕΙΟ!", // σε περίπτωση αποτυχίας εμφανίζουμε μήνυμα λάθους
            myStore.get_storeName(), JOptionPane.ERROR_MESSAGE);
        }
        else{
            DisplaySellsinTable(); // εμφανίζουμε τις συσκευές στον πίνακα πωλήσεων και εμφανίζουμε και κατάλληλο μήνυμα
            JOptionPane.showMessageDialog(null,"ΟΙ ΠΡΟΚΑΘΟΡΙΜΕΝΕΣ ΤΙΜΕΣ ΦΟΡΤΩΘΗΚΑΝ ΕΠΙΤΥΓΧΩΣ!",
                            myStore.get_storeName(), JOptionPane.INFORMATION_MESSAGE);
       }       
   }

   // 5. Φόρτωση παραγγελιών από αρχείο 
   public void LoadOrders(){
       int i; String filename;
        String userDir = System.getProperty("user.dir"); // To directory της εφαρμογής
        JFileChooser choosefile = new JFileChooser(userDir); // επιτρέπει την επιλογή αρχείου για άνοιγμα
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        filename=null;
       if (choosefile.showOpenDialog(menu1)==JFileChooser.APPROVE_OPTION)
                filename = choosefile.getSelectedFile().getAbsolutePath();
        if (myStore.LoadOrdersSalesFromFile(false, filename)==0){
            JOptionPane.showMessageDialog(null, "ΟΙ ΠΡΟΚΑΘΟΡΙΜΕΝΕΣ ΤΙΜΕΣ ΦΟΡΤΩΘΗΚΑΝ ΕΠΙΤΥΓΧΩΣ!",
            myStore.get_storeName(), JOptionPane.ERROR_MESSAGE);
        }
        else{
            this.DisplayOrdersinTable();
            JOptionPane.showMessageDialog(null,"ΟΙ ΠΡΟΚΑΘΟΡΙΜΕΝΕΣ ΤΙΜΕΣ ΦΟΡΤΩΘΗΚΑΝ ΕΠΙΤΥΓΧΩΣ!",
                            myStore.get_storeName(), JOptionPane.INFORMATION_MESSAGE);
       }
   }

   // 6. Αποθήκευση Διαθέσιμων Συσκευών σε αρχείο
   public void WriteDevicesToFile(){
        boolean result;String filename=null;
        String userDir = System.getProperty("user.dir"); // To directory της εφαρμογής
        JFileChooser choosefile = new JFileChooser(userDir); // επιτρέπει την επιλογή αρχείου για άνοιγμα
        if (choosefile.showSaveDialog(menu1)==JFileChooser.APPROVE_OPTION)
             filename = choosefile.getSelectedFile().getAbsolutePath();
        result = myStore.WriteAvailableDevicesToFile(filename); // καλούμε την αντίστοιχη μέθοδο του Store
        if (result==true)
            JOptionPane.showMessageDialog(null,"ΕΥΧΑΡΙΣΤΟΥΜΕ!!!",
                   myStore.get_storeName(), JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null,"ΑΠΟΤΥΧΙΑ ΑΠΟΘΗΚΕΥΣΗΣ!",
                   myStore.get_storeName(), JOptionPane.ERROR_MESSAGE);
   }

   // 7. Αποθήκευση πωλήσεων σε αρχείο
   public void WriteSalestoFile(){
       Boolean result;String filename=null;
       String userDir = System.getProperty("user.dir"); // To directory της εφαρμογής
       JFileChooser choosefile = new JFileChooser(userDir); // επιτρέπει την επιλογή αρχείου για άνοιγμα
        if (choosefile.showSaveDialog(menu1)==JFileChooser.APPROVE_OPTION)
             filename = choosefile.getSelectedFile().getAbsolutePath();
       result = myStore.WriteOrdersSalesToFile(false,filename); // καλόυμε την αντίστοιχη μέθοδο του Store
       if (result==true)
        JOptionPane.showMessageDialog(null,"ΕΥΧΑΡΙΣΤΟΥΜΕ!!!!",
          myStore.get_storeName(), JOptionPane.INFORMATION_MESSAGE);
       else
        JOptionPane.showMessageDialog(null,"ΤΤΤΤΤΤΤΤΤΤΤΤΤΤΤΤΤΤΤ!",
          myStore.get_storeName(), JOptionPane.ERROR_MESSAGE);
   }

   // 8. Αποθήκευση παραγγελιών σε αρχείο, λειτουργεί με παρόμοιο τρόπο όπως η προηγούμενη
   public void WriteOrderstoFile(){
       Boolean result;String filename=null;
       String userDir = System.getProperty("user.dir"); // To directory της εφαρμογής
       JFileChooser choosefile = new JFileChooser(userDir); // επιτρέπει την επιλογή αρχείου για άνοιγμα
        if (choosefile.showSaveDialog(menu1)==JFileChooser.APPROVE_OPTION)
             filename = choosefile.getSelectedFile().getAbsolutePath();
       result = myStore.WriteOrdersSalesToFile(true,filename);
       if (result==true)
        JOptionPane.showMessageDialog(null,"ΕΧΧΑΡΙΣΤΟΥΜΕ!!!",
          myStore.get_storeName(), JOptionPane.INFORMATION_MESSAGE);
       else
        JOptionPane.showMessageDialog(null,"ΑΠΟΤΥΧΙΑ ΑΠΟΘΗΚΕΥΣΗΣ!",
          myStore.get_storeName(), JOptionPane.ERROR_MESSAGE);
   }

   
  // 9. Εμφάνιση πλαισίου διαλόγου με τις πληροφορίες της συσκευής
    public void showDeviceBox() {
        if (deviceInfoBox == null) {
            deviceInfoBox = new DeviceInfoBox(this);                    
            deviceInfoBox.setLocationRelativeTo(this);
        }
        deviceInfoBox.setInformation();
        deviceInfoBox.setVisible(true);
    }
    
   // 10. Παραλαβή παραγγελίας και πώληση συσκευής
   public void ReceiveOrderAndSell(){
        if (jTableOrders.getSelectedRow()!=-1){ // αν ο χρήστης έχει επιλέξει παραγγελία
            selectedDevice=myStore.get_Availablesuskeui(jTableOrders.getSelectedRow());  // λαμβάνουμε τη συσκευή που αντιστοιχεί στην παραγγελία αυτή
            myStore.Receiveparaggelia(jTableOrders.getSelectedRow());   // Καλούμε την αντίστοιχη μέθοδο του Store
            this.DisplaySellsinTable(); // ενημερώνουμε τους πίνακες που εμφανίζουν πωλήσεις...
            this.DisplayOrdersinTable(); // παραγγελίες
            this.DisplayDevicesinTable(); // και συσκευές
            JOptionPane.showMessageDialog(null,"Η ΠΑΡΑΓΓΕΛΙΑ ΚΑΤΑΧΩΡΗΘΗΚΕ!",
                myStore.get_storeName(), JOptionPane.INFORMATION_MESSAGE);
        }
        else
        JOptionPane.showMessageDialog(null,"ΚΑΜΙΑ ΠΑΡΑΓΓΕΛΙΑ ΔΕΝ ΚΑΤΑΧΩΡΗΘΗΚΕ!",myStore.get_storeName(), JOptionPane.ERROR_MESSAGE);
   }

    
    // 11. Έξοδος από την εφαρμογή
    public void Exit(){
       int selectedValue=JOptionPane.showConfirmDialog(null,
                        "ΚΛΕΙΣΙΜΟ ΚΑΙ ΑΠΟΘΗΕΚΥΣΗ ΟΛΩΝ?",myStore.get_storeName(), JOptionPane.YES_NO_OPTION);
       if (selectedValue==JOptionPane.YES_OPTION) {
           myStore.WriteAvailableDevicesToFile(null);
           myStore.WriteOrdersSalesToFile(false, null);
           myStore.WriteOrdersSalesToFile(true, null);
           System.exit(0);
       }

    }
   public void mousePressed(MouseEvent e) {} 
   public void mouseExited(MouseEvent e){}
   public void mouseEntered(MouseEvent e){}    
   public void mouseReleased(MouseEvent e){}    
   public void mouseClicked(MouseEvent e){
        if (e.getSource()==this.jTableAvailableDevices)
            if (e.getClickCount()==2){ // αν το πλήθος των κλικ που κάνει ο χρήστης είναι δύο
                e.consume(); // τότε έχουμε double click, με το consume λαμβάνουμε το γεγονός
                selectedDevice=myStore.get_Availablesuskeui(jTableAvailableDevices.getSelectedRow()); // εμφανίζουμε το πλαίσιο διαλόγου με τις πληροφορίες της συσκευής
                showDeviceBox();
            }
            
   }

   public void actionPerformed(ActionEvent e){
            // Επιλογή φόρτωσης διαθέσιμων συσκευών
            if(e.getSource()==jmLoadAvailableDevices){
                LoadAvailableDevices();
            }
            // Επιλογή φόρτωσης παραγγελιών
            if(e.getSource()==jmLoadOrdersList){
                LoadOrders();
            }
            // Επιλογή φόρτωσης πωλήσεων
            if(e.getSource()==jmLoadSalesList){
                LoadSales();
            }
            // Επιλογή αποθήκευσης διαθέσιμων συσκευών
            if (e.getSource()==jmSaveAvailableDevices){
                WriteDevicesToFile();
            }
            // Επιλογή αποθήκευσης παραγγελιών
            if (e.getSource()==jmSaveOrdersList){
                WriteOrderstoFile();
            }
            // Επιλογή αποθήκευσης πωλήσεων
            if (e.getSource()==jmSaveSalesList){
                WriteSalestoFile();
            }
            // Επιλογή εξόδου από την εφαρμογή
            if (e.getSource()==jmExit){
                Exit();
            }
            // Επιλογή about...
            if (e.getSource()==jmAbout)
             JOptionPane.showMessageDialog(null,
                    "Γκότσης Δημήτριος,Κανελλόπουλος Νικόλαος,Κοντούλης Κωνσταντίνος",
                    myStore.get_storeName(), JOptionPane.INFORMATION_MESSAGE);
            // Επιλογή νέας πώλησης
            if (e.getSource()==tbuttonSell){
                // Νέα πώληση
                Sell();
            }
            // Επιλογή νέας παραγγελίας
            if (e.getSource()==tbuttonOrder){
                Order();
            }
            if (e.getSource()==this.tbuttonOrderSell){
                ReceiveOrderAndSell();
            }

        }
 
//κατασκευή γραμμής μενού

    private void  DrawMenu(){

        // Κατασκευή δύο μενού
        menu1 = new JMenu("Αρχείο");
        menu2 = new JMenu("Creators...");
        jmLoadAvailableDevices = new JMenuItem("Φόρτωση διαθέσιμων συσκευών");
        jmLoadAvailableDevices.addActionListener(this);
        jmLoadOrdersList  = new JMenuItem("Φόρτωση λίστας παραγγελιών");
        jmLoadOrdersList.addActionListener(this);
        jmLoadSalesList = new JMenuItem("Φόρτωση λίστας πωλήσεων");
        jmLoadSalesList.addActionListener(this);
        jmSaveAvailableDevices = new JMenuItem("Αποθήκευση διαθέσιμων συσκευών");
        jmSaveAvailableDevices.addActionListener(this);
        jmSaveOrdersList = new JMenuItem("Αποθήκευση λίστας παραγγελιών");
        jmSaveOrdersList.addActionListener(this);
        jmSaveSalesList = new JMenuItem("Αποθήκευση λίστας πωλήσεων");
        jmSaveSalesList.addActionListener(this);
        jmExit = new JMenuItem("Έξοδος");
        jmExit.addActionListener(this);
        jmAbout = new JMenuItem("About");
        jmAbout.addActionListener(this);
        menu1.add(jmLoadAvailableDevices);
        menu1.add(jmLoadOrdersList);
        menu1.add(jmLoadSalesList);
        menu1.addSeparator();
        menu1.add(jmSaveAvailableDevices);
        menu1.add(jmSaveOrdersList);
        menu1.add(jmSaveSalesList);
        menu1.addSeparator();
        menu1.add(jmExit);
        menu2.add(jmAbout);
        jmMenuBar.add(menu1);
        jmMenuBar.add(menu2);
    }

    //γραμμη εργαλείων
  private void DrawToolbar(){
      toolbar = new JToolBar("toolbar", JToolBar.HORIZONTAL);
      tbuttonSell = new JButton(new ImageIcon("Images/Sell.jpg"));
      tbuttonSell.setToolTipText("Πώληση συσκευής");
      tbuttonSell.addActionListener(this);
      toolbar.add(tbuttonSell);
      tbuttonOrder = new JButton(new ImageIcon("Images/order.jpg"));
      tbuttonOrder.setToolTipText("Παραγγελία συσκευής");
      tbuttonOrder.addActionListener(this);
      toolbar.add(tbuttonOrder);
      tbuttonOrderSell = new JButton(new ImageIcon("Images/ordersell.jpg"));
      tbuttonOrderSell.setToolTipText("Παραγγελία και πώληση");
      tbuttonOrderSell.addActionListener(this);
      toolbar.add(tbuttonOrderSell);
      add(toolbar,BorderLayout.NORTH);
      
  }
    

  private void DrawStatusBar(){
      statusbar = new JLabel();
      statusbar.setPreferredSize(new Dimension(100, 16));
      statusbar.setText(" "+"Ready");
      add(statusbar, java.awt.BorderLayout.SOUTH);
  }
  
//καρτέλες
    private void DrawAllTabs() {
         tabs = new JTabbedPane();
        // Προσθηκη της 1ης καρτέλας
        JPanel panel1 = new JPanel();
        JLabel label = new JLabel("ΔΙΑΘΕΣΙΜΕΣ ΣΥΣΚΕΥΕΣ.ΔΙΠΛΟ ΚΛΙΚ ΓΙΑ ΠΕΡΙΣΣΟΤΕΡΕΣ ΠΛΗΡΟΦΟΡΙΕΣ...");
        BorderLayout f1 = new BorderLayout();
        panel1.setLayout(f1);
        panel1.add(label,BorderLayout.PAGE_START);
        jTableAvailableDevicesModel = new DefaultTableModel ();
        jTableAvailableDevicesModel.addColumn("όνομα μοντέλου");
        jTableAvailableDevicesModel.addColumn("έτος κατασκευής");
        jTableAvailableDevicesModel.addColumn("έτος μοντέλου");
        jTableAvailableDevicesModel.addColumn("τιμή (w/o έκπτωση)");
        jTableAvailableDevicesModel.addColumn("διαθεσιμότητα");
        jTableAvailableDevices = new JTable(jTableAvailableDevicesModel){ 
            public boolean isCellEditable(int x, int y){ return false;}
        };
        JTableHeader header = jTableAvailableDevices.getTableHeader();
        header.setBackground(Color.red);
        JScrollPane listScroller = new JScrollPane(jTableAvailableDevices);
        jTableAvailableDevices.addMouseListener(this);
	jTableAvailableDevices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	listScroller.setPreferredSize(new Dimension(500, 200));
        panel1.add(listScroller, BorderLayout.CENTER);
        tabs.addTab("ΔΙΑΘΕΣΙΜΕΣ ΣΥΣΚΕΥΕΣ", null, panel1,null);
        tabs.setMnemonicAt(0, KeyEvent.VK_1);


        // Προσθηκη της 2ης καρτέλας
        JPanel panel2 = new JPanel();
        JLabel label2 = new JLabel("ΛΙΣΤΑ ΠΩΛΗΣΕΩΝ..");
        BorderLayout f2 = new BorderLayout();
        panel2.setLayout(f2);
        panel2.add(label2,BorderLayout.PAGE_START);
            //Προσθήκη πίνακα πωλήσεων
        jTableSalesModel = new DefaultTableModel ();
        jTableSalesModel.addColumn("κωδικός");
        jTableSalesModel.addColumn("όνομα μοντέλου");
        jTableSalesModel.addColumn("όνομα πελάτη");
        jTableSalesModel.addColumn("τηλέφωνο πελάτη");
        jTableSalesModel.addColumn("ημερομηνία πώλησης");
        jTableSales = new JTable(jTableSalesModel){
            public boolean isCellEditable(int x, int y){ return false;}
        };
        JTableHeader header2 = jTableSales.getTableHeader();
        header2.setBackground(Color.cyan);
        JScrollPane listScroller2 = new JScrollPane(jTableSales);
        jTableSales.addMouseListener(this);
	jTableSales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	listScroller2.setPreferredSize(new Dimension(500, 200));
        panel2.add(listScroller2, BorderLayout.CENTER);
        tabs.addTab("ΠΩΛΗΣΕΙΣ", null, panel2,null);
        tabs.setMnemonicAt(0, KeyEvent.VK_2);        
               
        // Προσθηκη της 3ης καρτέλας
        JPanel panel3 = new JPanel();
        JLabel label3 = new JLabel("ΛΙΣΤΑ ΠΑΡΑΓΓΕΛΙΩΝ..");
        BorderLayout f3 = new BorderLayout();
        panel3.setLayout(f3);
        panel3.add(label3,BorderLayout.PAGE_START);
            //Προσθήκη πίνακα πωλήσεων
        jTableOrdersModel = new DefaultTableModel ();
        jTableOrdersModel.addColumn("κωδικός");
        jTableOrdersModel.addColumn("όνομα μοντέλου");
        jTableOrdersModel.addColumn("όνομα πελάτη");
        jTableOrdersModel.addColumn("τηλέφωνο πελάτη");
        jTableOrdersModel.addColumn("τηλέφωνο πελάτη");
        jTableOrdersModel.addColumn("ημερομηνία πώλησης");
        jTableOrders = new JTable(jTableOrdersModel){ 
            public boolean isCellEditable(int x, int y){ return false;}
        };
        JTableHeader header3 = jTableOrders.getTableHeader();
        header3.setBackground(Color.green);
        JScrollPane listScroller3 = new JScrollPane(jTableOrders);
        jTableOrders.addMouseListener(this);
	jTableOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	listScroller3.setPreferredSize(new Dimension(550, 200));
        panel3.add(listScroller3, BorderLayout.CENTER);
        tabs.addTab("ΠΑΡΑΓΓΕΛΙΕΣ", null, panel3,null);
        tabs.setMnemonicAt(0, KeyEvent.VK_3);        
        //προσθήκη του tab στο panel
        add(tabs);
        //Ενεργοποίηση scrolling tabs.
        tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }



    //κεντρικο παραθυρο
    private void drawFrame(){
        setBounds(10, 10, 500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(true);
        //μενού επιλογών
        DrawMenu();
        setJMenuBar(jmMenuBar);
        //γραμμή εργαλείων
        DrawToolbar();
        // Τοποθέτηση του tab
        DrawAllTabs();
        add(tabs, BorderLayout.CENTER);
        //γραμμή κατάστασης
        DrawStatusBar();
        // Εμφάνιση του παραθύρου της εφαρμογής
        setVisible(true);
        pack();
    }

    // Ο κατασκευαστής της κλάσης
    public Application(Store s){
        super();
        this.myStore = s;
        // παράθυρο της εφαρμογής.
        setTitle(myStore.get_storeName());
        BorderLayout bl = new BorderLayout();
        setLayout(bl);
        //γραμμή μενού
        this.jmMenuBar = new JMenuBar();
        // Ζωγραφιά του και εμφάνισή του
        drawFrame();
    }
}


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		// Δημιουργία Καταστήματος
                Store myStore = new Store("THE THREE MUSKETTEERS");
                // Εμφάνιση Γραφικού Περιβάλλοντος της Εφαρμογής
                Application myStoreApp = new Application(myStore);
            }
        });
    }
}

    
