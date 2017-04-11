
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * Java Calculator 
 * Class: Java Programming 
 * Group Name: David, Gukyoung
 */
public class CalculatorFrame extends javax.swing.JFrame
{

    private double curOperand;
    private double prevOperand;
    private boolean justFinishedOperation  = false;
    private boolean prevFunction = false;
    private boolean isUndefined  = false;
    private boolean functionBusy = false;
    
    private boolean plus       = false;
    private boolean minus      = false;
    private boolean multiply   = false;
    private boolean divide     = false;
    private boolean module     = false;
    private boolean pressedDot = false;
    private int countSinceDot  = 0;
   
    private static String operator   = " ";
    private static double signedPrev = 1.0;
    private static double signedCur  = 1.0;
    private static int oprandCount   = 1;
    
    public CalculatorFrame() 
    {
        OperatorHandler operatorHandler = new OperatorHandler();
        DigitHandler digitHandler = new DigitHandler();
        ClearHandler clearHandler = new ClearHandler();
        FunctionHandler functionHandler = new FunctionHandler();
        ParenthesisHandler parenthesisHandler = new ParenthesisHandler();
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
        jButtonModule.addActionListener(operatorHandler);
        jButtonEqual.addActionListener(operatorHandler);
         
        jButtonπ.addActionListener(functionHandler);
        jButtonE.addActionListener(functionHandler);
        jButtonSquare.addActionListener(functionHandler);
        jButtonCube.addActionListener(functionHandler);
        jButtonEPowerX.addActionListener(functionHandler);
        jButtonSinX.addActionListener(functionHandler);
        jButtonCosX.addActionListener(functionHandler);
        jButtonTanX.addActionListener(functionHandler);
        jButton1DivX.addActionListener(functionHandler);
        jButtonSQRT.addActionListener(functionHandler);
        jButtonSigned.addActionListener(functionHandler);
        jButtonLnX.addActionListener(functionHandler);
         
        jButtonLParenthsis.addActionListener(parenthesisHandler);
        jButtonRParenthsis.addActionListener(parenthesisHandler);
        jButtonClear.addActionListener(clearHandler);
        
        display();
        this.setTitle("Basic Scientific Calculator");
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
            if (prevFunction) 
            {
                resetC();
                prevFunction = false;
            }
            
            String pattern = "#.";
            for (int i = 0; i < countSinceDot; i++) 
            {
                pattern += "0";
            }
            DecimalFormat dec = new DecimalFormat(pattern);
            //needs to refresh after operation
            String digitPressed = e.getActionCommand();
            if (curOperand > 99999999 || countSinceDot > 9)
            {
                //1000000000
                //i dunno call the guy a bellend
            } 
            else
            {
                if (!pressedDot) 
                {
                    curOperand *= 10;//shifting left
                } 
                else 
                {
                    countSinceDot++;
                    pattern += "0";
                }
                dec = new DecimalFormat(pattern);
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
                        if (!pressedDot) 
                        {
                            pressedDot = true;
                            curOperand /= 10;
                        }else
                        {
                            countSinceDot--;
                        }
                }
            }
            
            curOperand = Math.round(curOperand*Math.pow(10, 9))/Math.pow(10, 9);
            //no operater boolean true
            if(!plus&&!minus&&!multiply&&!divide&&!module == true)
            {
                JCalcField.setText("" + dec.format(signedPrev*curOperand));
            }
            else
            {
                //one operator boolean true
                JCalcField.setText((signedPrev*prevOperand) + operator + dec.format(signedCur*curOperand));
            } 
        }   
    }
    
    private class OperatorHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            prevFunction = false;
            //needs to account any subsequent clicks of plus
            String operatorPressed = e.getActionCommand();
            oprandCount++;
            //put resetBoolean() in each case instead?
            if(pressedDot)
            {
                resetBoolean();
                pressedDot = false;                
            }
            else if (!pressedDot)
            {
                resetBoolean();
            }   
            
            switch(operatorPressed)
            {
                case "+":
                    operator = " + ";
                    prevOperand = curOperand;
                    curOperand = 0;
                    JCalcField.setText((signedPrev*prevOperand) + " + " + (signedCur*curOperand));
                    plus = true;
                    break;          
                case "-":
                    operator = " - ";
                    prevOperand = curOperand;
                    curOperand = 0;
                    JCalcField.setText((signedPrev*prevOperand) + " - " + (signedCur*curOperand));
                    minus = true;
                    break;              
                case "X":
                    operator = " * ";
                    prevOperand = curOperand;
                    curOperand = 0;
                    JCalcField.setText((signedPrev*prevOperand) + " * " + (signedCur*curOperand));
                    multiply = true;
                    break; 
                case "÷":
                    operator = " ÷ ";
                    prevOperand = curOperand;
                    curOperand = 0;
                    divide = true;
                    JCalcField.setText((signedPrev*prevOperand) + " ÷ " + (signedCur*curOperand));
                    break;
                case "%":
                    operator = " % ";
                    prevOperand = curOperand;
                    curOperand = 0;
                    JCalcField.setText((signedPrev*prevOperand) + " % " + (signedCur*curOperand));
                    module = true;
                    break;    
                case "=":
                    double result = 0;
                    switch (operator)
                    {
                        case " + ":
                            result = (signedPrev*prevOperand) + (signedCur*curOperand);
                            plus = false;
                            justFinishedOperation = true;
                            break;
                              
                        case " - ":
                            result = (signedPrev*prevOperand) - (signedCur*curOperand);
                            minus = false;
                            justFinishedOperation = true;
                            break;   
                            
                        case " * ":
                            result = (signedPrev*prevOperand) * (signedCur*curOperand);
                            multiply = false;
                            justFinishedOperation = true;
                            break;
                        case " ÷ ":
                            if (curOperand == 0)
                            {
                                isUndefined = true;
                                break;
                            }    
                            result = (signedPrev*prevOperand) / (signedCur*curOperand);
                            divide = false;
                            justFinishedOperation = true;
                            break;
                        case " % ":
                            result = (signedPrev*prevOperand) % (signedCur*curOperand);
                            module = false;
                            justFinishedOperation = true;
                            break;                   
                    }
                    if (isUndefined)    
                    {
                        JCalcField.setText("Undefined!");
                        isUndefined = false;
                        break;
                    }   
                    else
                    {    
                        result = Math.round(result*Math.pow(10, 9))/Math.pow(10, 9);
                    }    
                //finally clause of "="
                
                JCalcField.setText((signedPrev*prevOperand) + operator + (signedCur*curOperand) + " = " + result);
                operator = " ";
            }    
        }
    }
    
    private class FunctionHandler implements ActionListener
    { 
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            String input = e.getActionCommand();
            if (justFinishedOperation == true) 
            {
                resetC();
                justFinishedOperation = false;
            }
            switch(input)
            {    
                case"π":
                    functionBusy = true;
                    curOperand += Math.PI * Math.pow(10, countSinceDot); 
                    break;
                case"e":
                    functionBusy = true;
                    curOperand += Math.E * Math.pow(10, countSinceDot);    
                    break;
                case"^2":
                    functionBusy = true;
                    prevFunction = true;
                    curOperand = Math.pow(curOperand, 2) * Math.pow(10, (0));    
                    break;
                case"^3":
                    functionBusy = true;
                    prevFunction = true;
                    curOperand = Math.pow(curOperand, 3) * Math.pow(10, (0));    
                    break;  
                case"e^x":
                    functionBusy = true;
                    curOperand = Math.pow(Math.E, curOperand) * Math.pow(10, (0));   
                    break; 
                 case"sinx":
                    functionBusy = true;
                    curOperand = Math.sin(curOperand) * Math.pow(10, (0));   
                    break;   
                case"cosx":
                    functionBusy = true;
                    curOperand = Math.cos(curOperand) * Math.pow(10, (0));    
                    break;
                case"tanx":
                    functionBusy = true;
                    curOperand = Math.tan(curOperand) * Math.pow(10, (0));     
                    break;    
                case"1/x":
                    functionBusy = true;
                    if (curOperand == 0) 
                    {
                        isUndefined = true;
                        break;
                    }
                    curOperand = 1/(curOperand  * Math.pow(1, (countSinceDot)));    
                    break; 
                case"+/-":
                    functionBusy = true;
                    if (oprandCount == 1) 
                    {
                        signedPrev = (signedPrev == 1.0)? -1.0:1.0;
                        break;
                    }
                    else if (oprandCount == 2)
                    {
                        signedCur  = (signedCur == 1.0)? -1.0:1.0;   
                        break;
                    }    
                case"SQRT":
                    functionBusy = true;
                    if (signedPrev == -1) 
                    {
                        isUndefined = true;
                        break;
                    }
                    curOperand = Math.sqrt(curOperand * Math.pow(1, (countSinceDot))); 
                    break;       
                case"lnx":
                    functionBusy = true;
                    if (curOperand <= 0 || signedPrev == -1) 
                    {
                        isUndefined = true;
                        break;
                    }
                    curOperand = Math.log(curOperand) * Math.pow(10, (0));
                    break;  
            }
            if (isUndefined) 
            {
                JCalcField.setText("Undefined!");
                isUndefined = false;
            }
            else
            {
                if (functionBusy) 
                {
                    curOperand = Math.round(curOperand*Math.pow(10, 9))/Math.pow(10, 9);
                    if(!plus&&!minus&&!multiply&&!divide&&!module == true)
                    {
                        JCalcField.setText(""+(signedPrev*curOperand));
                    }
                    else
                    {
                         //one operator boolean true
                        JCalcField.setText((signedPrev*prevOperand) + operator + (signedCur*curOperand));
                    }       
                } 
                else{justFinishedOperation = true;}
            } 
        }   
    }        
    
    private class ParenthesisHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            String parenthesis = e.getActionCommand();
            
            switch(parenthesis)
            {
                case"(":
                    
                    //todo codes//
                    
                
                case")":    
            }    
        }
    }       
    
    private class ClearHandler implements ActionListener
    {
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
    
    private void resetC()
    {
        curOperand  = 0;
        prevOperand = 0;
        signedCur   = 1.0;
        signedPrev  = 1.0;
        oprandCount = 1;
        resetBoolean();
        JCalcField.setText("" + curOperand);
    }
    
    private void resetBoolean()
    {
        justFinishedOperation = false;
        pressedDot = false;
        countSinceDot = 0;
        plus = false;
        minus = false;
        multiply = false;
        divide = false;
        module = false;
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
        jButtonMultiply = new javax.swing.JButton();
        jButtonModule = new javax.swing.JButton();
        jButtonPlus = new javax.swing.JButton();
        jButtonMinus = new javax.swing.JButton();
        jButtonDivide = new javax.swing.JButton();
        jButtonEqual = new javax.swing.JButton();
        jButtonLParenthsis = new javax.swing.JButton();
        jButtonRParenthsis = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButtonEPowerX = new javax.swing.JButton();
        jButtonπ = new javax.swing.JButton();
        jButtonSQRT = new javax.swing.JButton();
        jButtonTanX = new javax.swing.JButton();
        jButtonE = new javax.swing.JButton();
        jButtonCube = new javax.swing.JButton();
        jButton1DivX = new javax.swing.JButton();
        jButtonLnX = new javax.swing.JButton();
        jButtonCosX = new javax.swing.JButton();
        jButtonSinX = new javax.swing.JButton();
        jButtonSquare = new javax.swing.JButton();
        jButtonSigned = new javax.swing.JButton();

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
        getContentPane().add(JCalcField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 415, 61));

        jButtonClear.setText("C");
        jButtonClear.setMaximumSize(null);
        jButtonClear.setMinimumSize(new java.awt.Dimension(41, 23));
        jButtonClear.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton9.setForeground(new java.awt.Color(0, 102, 102));
        jButton9.setText("9");
        jButton9.setMaximumSize(null);
        jButton9.setMinimumSize(new java.awt.Dimension(41, 23));
        jButton9.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton8.setForeground(new java.awt.Color(0, 102, 102));
        jButton8.setText("8");
        jButton8.setMaximumSize(null);
        jButton8.setMinimumSize(new java.awt.Dimension(41, 23));
        jButton8.setPreferredSize(new java.awt.Dimension(41, 23));

        jButtonDecimal.setForeground(new java.awt.Color(153, 0, 0));
        jButtonDecimal.setText(".");
        jButtonDecimal.setMaximumSize(null);
        jButtonDecimal.setMinimumSize(new java.awt.Dimension(41, 23));
        jButtonDecimal.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton5.setForeground(new java.awt.Color(0, 102, 102));
        jButton5.setText("5");
        jButton5.setMaximumSize(null);
        jButton5.setMinimumSize(new java.awt.Dimension(41, 23));
        jButton5.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton6.setForeground(new java.awt.Color(0, 102, 102));
        jButton6.setText("6");
        jButton6.setMaximumSize(null);
        jButton6.setMinimumSize(new java.awt.Dimension(41, 23));
        jButton6.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton7.setForeground(new java.awt.Color(0, 102, 102));
        jButton7.setText("7");
        jButton7.setMaximumSize(null);
        jButton7.setMinimumSize(new java.awt.Dimension(41, 23));
        jButton7.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton0.setForeground(new java.awt.Color(0, 102, 102));
        jButton0.setText("0");
        jButton0.setMaximumSize(null);
        jButton0.setMinimumSize(new java.awt.Dimension(41, 23));
        jButton0.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton1.setForeground(new java.awt.Color(0, 102, 102));
        jButton1.setText("1");
        jButton1.setMaximumSize(null);
        jButton1.setMinimumSize(new java.awt.Dimension(41, 23));
        jButton1.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton2.setForeground(new java.awt.Color(0, 102, 102));
        jButton2.setText("2");
        jButton2.setMaximumSize(null);
        jButton2.setMinimumSize(new java.awt.Dimension(41, 23));
        jButton2.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton3.setForeground(new java.awt.Color(0, 102, 102));
        jButton3.setText("3");
        jButton3.setMaximumSize(null);
        jButton3.setMinimumSize(new java.awt.Dimension(41, 23));
        jButton3.setPreferredSize(new java.awt.Dimension(41, 23));

        jButton4.setForeground(new java.awt.Color(0, 102, 102));
        jButton4.setText("4");
        jButton4.setMaximumSize(null);
        jButton4.setMinimumSize(new java.awt.Dimension(41, 23));
        jButton4.setPreferredSize(new java.awt.Dimension(41, 23));

        jButtonMultiply.setForeground(new java.awt.Color(0, 0, 255));
        jButtonMultiply.setText("X");
        jButtonMultiply.setMaximumSize(null);
        jButtonMultiply.setMinimumSize(new java.awt.Dimension(41, 23));
        jButtonMultiply.setPreferredSize(new java.awt.Dimension(41, 23));

        jButtonModule.setForeground(new java.awt.Color(0, 0, 255));
        jButtonModule.setText("%");
        jButtonModule.setMaximumSize(null);
        jButtonModule.setMinimumSize(new java.awt.Dimension(41, 23));
        jButtonModule.setPreferredSize(new java.awt.Dimension(41, 23));

        jButtonPlus.setForeground(new java.awt.Color(0, 0, 255));
        jButtonPlus.setText("+");
        jButtonPlus.setMaximumSize(null);

        jButtonMinus.setForeground(new java.awt.Color(0, 0, 255));
        jButtonMinus.setText("-");
        jButtonMinus.setMaximumSize(null);
        jButtonMinus.setMinimumSize(new java.awt.Dimension(41, 23));
        jButtonMinus.setPreferredSize(new java.awt.Dimension(41, 23));

        jButtonDivide.setForeground(new java.awt.Color(0, 0, 255));
        jButtonDivide.setText("÷");
        jButtonDivide.setMaximumSize(null);

        jButtonEqual.setForeground(new java.awt.Color(255, 0, 0));
        jButtonEqual.setText("=");
        jButtonEqual.setMaximumSize(null);

        jButtonLParenthsis.setText("(");
        jButtonLParenthsis.setMaximumSize(null);
        jButtonLParenthsis.setMinimumSize(new java.awt.Dimension(41, 23));
        jButtonLParenthsis.setPreferredSize(new java.awt.Dimension(41, 23));

        jButtonRParenthsis.setText(")");
        jButtonRParenthsis.setMaximumSize(null);
        jButtonRParenthsis.setMinimumSize(new java.awt.Dimension(41, 23));
        jButtonRParenthsis.setPreferredSize(new java.awt.Dimension(41, 23));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPlus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonMinus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDivide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonDecimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonModule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonMultiply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonLParenthsis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRParenthsis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEqual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton0, jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9, jButtonClear, jButtonDecimal, jButtonDivide, jButtonEqual, jButtonLParenthsis, jButtonMinus, jButtonModule, jButtonMultiply, jButtonPlus, jButtonRParenthsis});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPlus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonMinus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDivide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDecimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonModule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonMultiply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonEqual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRParenthsis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLParenthsis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton0, jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9, jButtonClear, jButtonDecimal, jButtonDivide, jButtonEqual, jButtonLParenthsis, jButtonMinus, jButtonModule, jButtonMultiply, jButtonPlus, jButtonRParenthsis});

        jButtonPlus.getAccessibleContext().setAccessibleName("");
        jButtonMinus.getAccessibleContext().setAccessibleName("");
        jButtonDivide.getAccessibleContext().setAccessibleName("");
        jButtonEqual.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 200, -1));
        jPanel1.getAccessibleContext().setAccessibleParent(jPanel1);

        jButtonEPowerX.setForeground(new java.awt.Color(0, 153, 51));
        jButtonEPowerX.setText("e^x");
        jButtonEPowerX.setAlignmentY(0.0F);

        jButtonπ.setForeground(new java.awt.Color(102, 102, 0));
        jButtonπ.setText("π");
        jButtonπ.setAlignmentY(0.0F);

        jButtonSQRT.setForeground(new java.awt.Color(255, 153, 0));
        jButtonSQRT.setText("SQRT");
        jButtonSQRT.setAlignmentY(0.0F);

        jButtonTanX.setForeground(new java.awt.Color(153, 0, 153));
        jButtonTanX.setText("tanx");
        jButtonTanX.setAlignmentY(0.0F);

        jButtonE.setForeground(new java.awt.Color(102, 102, 0));
        jButtonE.setText("e");
        jButtonE.setAlignmentY(0.0F);

        jButtonCube.setForeground(new java.awt.Color(0, 153, 0));
        jButtonCube.setText("^3");
        jButtonCube.setAlignmentY(0.0F);

        jButton1DivX.setForeground(new java.awt.Color(102, 153, 0));
        jButton1DivX.setText("1/x");
        jButton1DivX.setAlignmentY(0.0F);

        jButtonLnX.setForeground(new java.awt.Color(0, 153, 51));
        jButtonLnX.setText("lnx");
        jButtonLnX.setAlignmentY(0.0F);

        jButtonCosX.setForeground(new java.awt.Color(153, 0, 153));
        jButtonCosX.setText("cosx");
        jButtonCosX.setAlignmentY(0.0F);

        jButtonSinX.setForeground(new java.awt.Color(153, 0, 153));
        jButtonSinX.setText("sinx");
        jButtonSinX.setAlignmentY(0.0F);

        jButtonSquare.setForeground(new java.awt.Color(0, 153, 0));
        jButtonSquare.setText("^2");
        jButtonSquare.setAlignmentY(0.0F);

        jButtonSigned.setText("+/-");
        jButtonSigned.setAlignmentY(0.0F);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonSquare, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButtonCube, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton1DivX, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonSinX, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButtonCosX, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButtonTanX, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonEPowerX, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButtonLnX, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButtonSQRT))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonπ, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButtonE, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButtonSigned, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1DivX, jButtonCosX, jButtonCube, jButtonE, jButtonEPowerX, jButtonLnX, jButtonSQRT, jButtonSigned, jButtonSinX, jButtonSquare, jButtonTanX, jButtonπ});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSquare)
                    .addComponent(jButtonCube)
                    .addComponent(jButton1DivX))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSinX)
                    .addComponent(jButtonCosX)
                    .addComponent(jButtonTanX))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEPowerX)
                    .addComponent(jButtonLnX)
                    .addComponent(jButtonSQRT))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonπ)
                    .addComponent(jButtonE)
                    .addComponent(jButtonSigned)))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1DivX, jButtonCosX, jButtonCube, jButtonE, jButtonEPowerX, jButtonLnX, jButtonSQRT, jButtonSigned, jButtonSinX, jButtonSquare, jButtonTanX, jButtonπ});

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, -1));
        jPanel2.getAccessibleContext().setAccessibleParent(jPanel2);

        getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException 
               | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalculatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
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
    private javax.swing.JButton jButtonLParenthsis;
    private javax.swing.JButton jButtonLnX;
    private javax.swing.JButton jButtonMinus;
    private javax.swing.JButton jButtonModule;
    private javax.swing.JButton jButtonMultiply;
    private javax.swing.JButton jButtonPlus;
    private javax.swing.JButton jButtonRParenthsis;
    private javax.swing.JButton jButtonSQRT;
    private javax.swing.JButton jButtonSigned;
    private javax.swing.JButton jButtonSinX;
    private javax.swing.JButton jButtonSquare;
    private javax.swing.JButton jButtonTanX;
    private javax.swing.JButton jButtonπ;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration                   
}
