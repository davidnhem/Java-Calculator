
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Java calculator
 * 
 */
public class CalculatorFrame extends javax.swing.JFrame
{

    private double curOperand;
    private double prevOperand;
    private boolean justFinishedOperation  = false;
    
    private boolean plus     = false;
    private boolean minus    = false;
    private boolean multiply = false;
    private boolean divide   = false;
    private boolean pressedDot = false;
    private int countSinceDot = 0;
    private static String operator = " ";
    
    private void resetBoolean(){
        justFinishedOperation = false;
        pressedDot = false;
        countSinceDot = 0;
        plus = false;
        minus = false;
        multiply = false;
        divide = false;
    }
    
    public CalculatorFrame() 
    {
        OperatorHandler operatorHandler = new OperatorHandler();
        DigitHandler digitHandler = new DigitHandler();
        ClearHandler clearHandler = new ClearHandler();
        
        initComponents();
        
        operator = "";
        jButton1.addActionListener(digitHandler);
        jButton2.addActionListener(digitHandler);
        jButton3.addActionListener(digitHandler);
        jButton4.addActionListener(digitHandler);
        jButton5.addActionListener(digitHandler);
        jButton6.addActionListener(digitHandler);
        jButton7.addActionListener(digitHandler);
        jButton8.addActionListener(digitHandler);
        jButton9.addActionListener(digitHandler);
        jButton0.addActionListener(digitHandler);
        jButtonDecimal.addActionListener(digitHandler);

        
        jButtonPlus.addActionListener(operatorHandler);
        jButtonMinus.addActionListener(operatorHandler);
        jButtonMultiply.addActionListener(operatorHandler);
        jButtonDivide.addActionListener(operatorHandler);
        jButtonEqual.addActionListener(operatorHandler);
        
        
        jButtonClear.addActionListener(clearHandler);
        
        display();
        this.setTitle("Calculator");
        this.setLocationRelativeTo(null);
    }
    
    public void display()
    {
        JCalcField.setText("Basic Calculator");
    }
    
    private class DigitHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            //needs to refresh after operation
            String digitPressed = e.getActionCommand();
            if(!pressedDot){
                curOperand *= 10;//shifting left
            }else{
                countSinceDot++;
            }

            
            if (justFinishedOperation == true) 
            {
                resetC();
                justFinishedOperation = false;
            }
            
            switch (digitPressed) 
            {
                case "1":
                    curOperand += 1 * Math.pow(10, -countSinceDot);
                    break;
                case "2":
                    curOperand += 2 * Math.pow(10, -countSinceDot);
                    break;
                case "3":
                    curOperand += 3 * Math.pow(10, -countSinceDot);
                    break;
                case "4":
                    curOperand += 4 * Math.pow(10, -countSinceDot);
                    break;
                case "5":
                    curOperand += 5 * Math.pow(10, -countSinceDot);
                    break;
                case "6":
                    curOperand += 6 * Math.pow(10, -countSinceDot);
                    break;
                case "7":
                    curOperand += 7 * Math.pow(10, -countSinceDot);
                    break;
                case "8":
                    curOperand += 8 * Math.pow(10, -countSinceDot);
                    break;
                case "9":
                    curOperand += 9 * Math.pow(10, -countSinceDot);
                    break;
                case "0":
                    curOperand += 0;
                    break; 
                case ".":
                    if(!pressedDot){
                        pressedDot = true;
                        curOperand /= 10;
                    }
                    
            }       
//            if(plus == false)
//            {
//                JCalcField.setText("" + curOperand);
//            }
//            else if(plus == true)
//            {
//                JCalcField.setText(prevOperand + " + " + curOperand);
//            }

            //no operater boolean true
            if(!plus&&!minus&&!multiply&&!divide == true){
                JCalcField.setText(""+curOperand);
            }else{
                //one operator boolean true
                JCalcField.setText(prevOperand + operator + curOperand);
            }
            
//            if(minus == false)
//            {
//                JCalcField.setText("" + result);
//            }    
//            else
//            {
//                JCalcField.setText(operand2 + " - " + result);
//            }    
        }
        
    }
    
    private class OperatorHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            //needs to account any subsequent clicks of plus
            String operatorPressed = e.getActionCommand();
            //put resetBoolean() here instead?
            switch(operatorPressed)
            {
                case "+":
                    operator = " + ";
                    prevOperand = curOperand;
                    curOperand = 0;
                    JCalcField.setText(prevOperand + " + " + curOperand);
                    resetBoolean();
                    plus = true;
                    break;
                    
                case "-":
                    operator = " - ";
                    prevOperand = curOperand;
                    curOperand = 0;
                    JCalcField.setText(prevOperand + " - " + curOperand);
                    resetBoolean();
                    minus = true;
                    break;   
                    
                case "X":
                    operator = " * ";
                    prevOperand = curOperand;
                    curOperand = 0;
                    JCalcField.setText(prevOperand + " * " + curOperand);
                    resetBoolean();
                    multiply = true;
                    break; 
                case "÷":
                    operator = " ÷ ";
                    prevOperand = curOperand;
                    curOperand = 0;
                    JCalcField.setText(prevOperand + " ÷ " + curOperand);
                    resetBoolean();
                    divide = true;
                    break;
                case "=":
                    double result = 0;
                    switch (operator)
                    {
                        case " + ":
                            result = prevOperand + curOperand;
                            JCalcField.setText(prevOperand + " + " + curOperand + " = " + result);
                            plus = false;
                            justFinishedOperation = true;
                            break;
                              
                        case " - ":
                            result = prevOperand - curOperand;
                            JCalcField.setText(prevOperand + " - " + curOperand + " = " + result);
                            minus = false;
                            justFinishedOperation = true;
                            break;   
                            
                         case " * ":
                            result = prevOperand * curOperand;
                            JCalcField.setText(prevOperand + " X " + curOperand + " = " + result);
                            multiply = false;
                            justFinishedOperation = true;
                            break;
                       case " ÷ ":
                            result = prevOperand / curOperand;
                            JCalcField.setText(prevOperand + " ÷ " + curOperand + " = " + result);
                            divide = false;
                            justFinishedOperation = true;
                            break;
                    }
                //finally clause of "="
                ;operator = " ";
            }    
        }
    }
    
    private class ClearHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //to C, or to CE?
            if(e.getSource().equals(jButtonClear))
            {
                resetC();
            }
        }
    }
    
    private void resetC(){
        curOperand = 0;
        prevOperand = 0;
        resetBoolean();
        JCalcField.setText(""+curOperand);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        JCalcField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButtonClear = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButtonDecimal = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton0 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButtonPlus = new javax.swing.JButton();
        jButtonMinus = new javax.swing.JButton();
        jButtonDivide = new javax.swing.JButton();
        jButtonEqual = new javax.swing.JButton();
        jButtonMultiply = new javax.swing.JButton();
        jButtonModule = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButtonTanX = new javax.swing.JButton();
        jButtonSquare = new javax.swing.JButton();
        jButtonCube = new javax.swing.JButton();
        jButtonπ = new javax.swing.JButton();
        jButtonEPowerX = new javax.swing.JButton();
        jButtonCosX = new javax.swing.JButton();
        jButton1DivX = new javax.swing.JButton();
        jButtonLnX = new javax.swing.JButton();
        jButtonSinX = new javax.swing.JButton();
        jButtonE = new javax.swing.JButton();
        jButtonSQRT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setBounds(new java.awt.Rectangle(240, 240, 240, 240));
        setLocation(new java.awt.Point(23, 56));
        setMaximumSize(null);
        setShape(getShape());
        setSize(new java.awt.Dimension(329, 228));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JCalcField.setEditable(false);
        JCalcField.setText("Basic Calculator");
        getContentPane().add(JCalcField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 410, 61));

        jButtonClear.setText("C");
        jButtonClear.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton9.setForeground(new java.awt.Color(0, 102, 102));
        jButton9.setText("9");
        jButton9.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton8.setForeground(new java.awt.Color(0, 102, 102));
        jButton8.setText("8");
        jButton8.setPreferredSize(new java.awt.Dimension(41, 23));

        jButtonDecimal.setForeground(new java.awt.Color(153, 0, 0));
        jButtonDecimal.setText(".");
        jButtonDecimal.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton5.setForeground(new java.awt.Color(0, 102, 102));
        jButton5.setText("5");
        jButton5.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton6.setForeground(new java.awt.Color(0, 102, 102));
        jButton6.setText("6");
        jButton6.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton7.setForeground(new java.awt.Color(0, 102, 102));
        jButton7.setText("7");
        jButton7.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton0.setForeground(new java.awt.Color(0, 102, 102));
        jButton0.setText("0");
        jButton0.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton1.setForeground(new java.awt.Color(0, 102, 102));
        jButton1.setText("1");
        jButton1.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton2.setForeground(new java.awt.Color(0, 102, 102));
        jButton2.setText("2");
        jButton2.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton3.setForeground(new java.awt.Color(0, 102, 102));
        jButton3.setText("3");
        jButton3.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton4.setForeground(new java.awt.Color(0, 102, 102));
        jButton4.setText("4");
        jButton4.setPreferredSize(new java.awt.Dimension(41, 23));

        jButtonPlus.setForeground(new java.awt.Color(0, 0, 255));
        jButtonPlus.setText("+");
        jButtonPlus.setMaximumSize(null);

        jButtonMinus.setForeground(new java.awt.Color(0, 0, 255));
        jButtonMinus.setText("-");
        jButtonMinus.setMaximumSize(null);
        jButtonMinus.setPreferredSize(new java.awt.Dimension(41, 23));

        jButtonDivide.setForeground(new java.awt.Color(0, 0, 255));
        jButtonDivide.setText("÷");
        jButtonDivide.setMaximumSize(null);
        jButtonDivide.setPreferredSize(new java.awt.Dimension(41, 23));

        jButtonEqual.setForeground(new java.awt.Color(255, 0, 0));
        jButtonEqual.setText("=");
        jButtonEqual.setMaximumSize(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonEqual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButtonDivide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButtonMinus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButtonPlus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonPlus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(jButtonMinus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(jButtonDivide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEqual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButtonPlus.getAccessibleContext().setAccessibleName("");
        jButtonPlus.getAccessibleContext().setAccessibleParent(jPanel1);
        jButtonMinus.getAccessibleContext().setAccessibleName("");
        jButtonMinus.getAccessibleContext().setAccessibleParent(jPanel1);
        jButtonDivide.getAccessibleContext().setAccessibleName("");
        jButtonDivide.getAccessibleContext().setAccessibleParent(jPanel1);
        jButtonEqual.getAccessibleContext().setAccessibleName("");
        jButtonEqual.getAccessibleContext().setAccessibleParent(jPanel1);

        jButtonMultiply.setForeground(new java.awt.Color(0, 0, 255));
        jButtonMultiply.setText("X");
        jButtonMultiply.setPreferredSize(new java.awt.Dimension(41, 23));
        jButtonMultiply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                none(evt);
            }
        });

        jButtonModule.setForeground(new java.awt.Color(0, 0, 255));
        jButtonModule.setText("%");
        jButtonModule.setPreferredSize(new java.awt.Dimension(41, 23));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jButtonDecimal, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(6, 6, 6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonModule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonMultiply, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jButton0, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDecimal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonMultiply, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonModule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 275, -1, -1));

        jButtonTanX.setForeground(new java.awt.Color(153, 0, 153));
        jButtonTanX.setText("tanx");

        jButtonSquare.setForeground(new java.awt.Color(0, 153, 0));
        jButtonSquare.setText("^2");

        jButtonCube.setForeground(new java.awt.Color(0, 153, 0));
        jButtonCube.setText("^3");

        jButtonπ.setForeground(new java.awt.Color(102, 102, 0));
        jButtonπ.setText("π");
        jButtonπ.setPreferredSize(new java.awt.Dimension(39, 23));

        jButtonEPowerX.setForeground(new java.awt.Color(0, 153, 51));
        jButtonEPowerX.setText("e^x");

        jButtonCosX.setForeground(new java.awt.Color(153, 0, 153));
        jButtonCosX.setText("cosx");

        jButton1DivX.setForeground(new java.awt.Color(102, 153, 0));
        jButton1DivX.setText("1/x");

        jButtonLnX.setForeground(new java.awt.Color(0, 153, 51));
        jButtonLnX.setText("lnx");

        jButtonSinX.setForeground(new java.awt.Color(153, 0, 153));
        jButtonSinX.setText("sinx");

        jButtonE.setForeground(new java.awt.Color(102, 102, 0));
        jButtonE.setText("e");

        jButtonSQRT.setForeground(new java.awt.Color(255, 153, 0));
        jButtonSQRT.setText("SQRT");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonEPowerX)
                            .addComponent(jButtonπ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonLnX)
                            .addComponent(jButtonE))
                        .addGap(6, 6, 6)
                        .addComponent(jButtonSQRT))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonSquare)
                            .addComponent(jButtonSinX))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jButtonCosX)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonTanX))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jButtonCube)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1DivX)))))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1DivX, jButtonCosX, jButtonCube, jButtonE, jButtonEPowerX, jButtonLnX, jButtonSQRT, jButtonSinX, jButtonSquare, jButtonTanX, jButtonπ});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSquare)
                    .addComponent(jButtonCube)
                    .addComponent(jButton1DivX))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSinX)
                    .addComponent(jButtonCosX)
                    .addComponent(jButtonTanX))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEPowerX)
                    .addComponent(jButtonLnX)
                    .addComponent(jButtonSQRT))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonπ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonE))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1DivX, jButtonCosX, jButtonCube, jButtonE, jButtonEPowerX, jButtonLnX, jButtonSQRT, jButtonSinX, jButtonSquare, jButtonTanX, jButtonπ});

        jButtonTanX.getAccessibleContext().setAccessibleParent(jPanel2);
        jButtonSquare.getAccessibleContext().setAccessibleParent(jPanel2);
        jButtonCube.getAccessibleContext().setAccessibleParent(jPanel2);
        jButtonπ.getAccessibleContext().setAccessibleParent(jPanel2);
        jButtonEPowerX.getAccessibleContext().setAccessibleParent(jPanel2);
        jButtonCosX.getAccessibleContext().setAccessibleParent(jPanel2);
        jButton1DivX.getAccessibleContext().setAccessibleParent(jPanel2);
        jButtonLnX.getAccessibleContext().setAccessibleParent(jPanel2);
        jButtonSinX.getAccessibleContext().setAccessibleParent(jPanel2);
        jButtonE.getAccessibleContext().setAccessibleParent(jPanel2);
        jButtonSQRT.getAccessibleContext().setAccessibleParent(jPanel2);

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, -1, -1));

        getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>                        

    private void none(java.awt.event.ActionEvent evt) {                      
        // TODO add your handling code here:
    }                     

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CalculatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalculatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalculatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalculatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalculatorFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextField JCalcField;
    private javax.swing.JButton jButton0;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton1DivX;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonCosX;
    private javax.swing.JButton jButtonCube;
    private javax.swing.JButton jButtonDecimal;
    private javax.swing.JButton jButtonDivide;
    private javax.swing.JButton jButtonE;
    private javax.swing.JButton jButtonEPowerX;
    private javax.swing.JButton jButtonEqual;
    private javax.swing.JButton jButtonLnX;
    private javax.swing.JButton jButtonMinus;
    private javax.swing.JButton jButtonModule;
    private javax.swing.JButton jButtonMultiply;
    private javax.swing.JButton jButtonPlus;
    private javax.swing.JButton jButtonSQRT;
    private javax.swing.JButton jButtonSinX;
    private javax.swing.JButton jButtonSquare;
    private javax.swing.JButton jButtonTanX;
    private javax.swing.JButton jButtonπ;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration                    
}
