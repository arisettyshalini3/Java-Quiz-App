// Importing necessary libraries
import java.awt.*; // For GUI design (buttons, labels, layout)
import java.awt.event.*; // For handling events like button clicks
import javax.swing.*; // For building GUI components using Swing
import java.util.*; // For using data structures like HashMap and Enumeration

// Quiz class that extends JFrame and implements ActionListener to create GUI and handle events
class Quiz extends JFrame implements ActionListener {
    // GUI components
    JPanel panel;
    JRadioButton choice1, choice2, choice3, choice4;
    ButtonGroup bg; // To group radio buttons
    JLabel lblmess;
    JButton btnext;

    // Arrays to store questions and correct answers
    String[][] qpa;
    String[][] qca;

    // Question ID tracker
    int qaid;

    // Map to store user selected answers
    HashMap<Integer, String> map;

    // Constructor: Initializes quiz window
    Quiz() {
        initializedata(); // Load questions and answers

        setTitle("Quiz Program");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(430, 350);
        setLocation(300, 100);
        setResizable(false);

        // Setting up layout and components
        Container cont = getContentPane();
        cont.setLayout(null);
        cont.setBackground(Color.GRAY);

        // Creating and grouping radio buttons
        bg = new ButtonGroup();
        choice1 = new JRadioButton("Choice1", true);
        choice2 = new JRadioButton("Choice2", false);
        choice3 = new JRadioButton("Choice3", false);
        choice4 = new JRadioButton("Choice4", false);
        bg.add(choice1); bg.add(choice2); bg.add(choice3); bg.add(choice4);

        // Setting up message label
        lblmess = new JLabel("Choose the correct answer");
        lblmess.setForeground(Color.BLUE);
        lblmess.setFont(new Font("Arial", Font.BOLD, 11));

        // Next button
        btnext = new JButton("Next");
        btnext.setForeground(Color.BLUE);
        btnext.addActionListener(this);

        // Creating and adding components to panel
        panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLocation(10, 10);
        panel.setSize(400, 300);
        panel.setLayout(new GridLayout(6, 1));
        panel.add(lblmess);
        panel.add(choice1);
        panel.add(choice2);
        panel.add(choice3);
        panel.add(choice4);
        panel.add(btnext);
        cont.add(panel);

        // Make window visible
        setVisible(true);
        qaid = 0;
        readqa(qaid); // Load first question
    }

    // Handle button click events
    public void actionPerformed(ActionEvent e) {
        if (btnext.getText().equals("Next")) {
            map.put(qaid, getSelection()); // Store user's choice
            qaid++;
            if (qaid == qpa.length - 1) {
                btnext.setText("Show Answers");
            }
            if (qaid < qpa.length) {
                readqa(qaid); // Load next question
            }
        } else if (btnext.getText().equals("Show Answers")) {
            map.put(qaid, getSelection());
            new Report(); // Show result
        }
    }

    // Get the selected radio button option
    public String getSelection() {
        String selectedChoice = null;
        Enumeration<AbstractButton> buttons = bg.getElements();
        while (buttons.hasMoreElements()) {
            JRadioButton temp = (JRadioButton) buttons.nextElement();
            if (temp.isSelected()) {
                selectedChoice = temp.getText();
            }
        }
        return selectedChoice;
    }

    // Load questions and options
    public void readqa(int qid) {
        lblmess.setText("  " + qpa[qid][0]);
        choice1.setText(qpa[qid][1]);
        choice2.setText(qpa[qid][2]);
        choice3.setText(qpa[qid][3]);
        choice4.setText(qpa[qid][4]);
        choice1.setSelected(true);
    }

    // Load data
    public void initializedata() {
        qpa = new String[10][5];
        qpa[0][0] = "How to run Java program on the command prompt?";
        qpa[0][1] = "javac JavaProgram";
        qpa[0][2] = "java JavaProgram";
        qpa[0][3] = "run JavaProgram";
        qpa[0][4] = "execute JavaProgram";

        qpa[1][0] = "What is the use of the println method?";
        qpa[1][1] = "It is used to read input";
        qpa[1][2] = "It is used to break loop";
        qpa[1][3] = "It is used to print text on the screen with the line break.";
        qpa[1][4] = "None of the above";

        qpa[2][0] = "How to read a character from the keyboard?";
        qpa[2][1] = "char c=System.in.read()";
        qpa[2][2] = "char c=Scanner.next()";
        qpa[2][3] = "char c=nextChar()";
        qpa[2][4] = "char c=(char)System.in.read()";

        qpa[3][0] = "Which one would be an int?";
        qpa[3][1] = "2";
        qpa[3][2] = "2.0";
        qpa[3][3] = "'2'";
        qpa[3][4] = "None";

        qpa[4][0] = "How do you declare an integer variable x?";
        qpa[4][1] = "int x";
        qpa[4][2] = "x int";
        qpa[4][3] = "integer x";
        qpa[4][4] = "x = int";

        qpa[5][0] = "How do you convert a string of number to a number?";
        qpa[5][1] = "int num = Integer.parseInt(str_num)";
        qpa[5][2] = "int num = toInt(str_num)";
        qpa[5][3] = "parseInt(str_num)";
        qpa[5][4] = "Integer(str_num)";

        qpa[6][0] = "What is the value of x? int x=3>>2";
        qpa[6][1] = "1";
        qpa[6][2] = "0";
        qpa[6][3] = "3";
        qpa[6][4] = "-3";

        qpa[7][0] = "How to exit a loop?";
        qpa[7][1] = "Using exit";
        qpa[7][2] = "Using break";
        qpa[7][3] = "Using continue";
        qpa[7][4] = "Using terminate";

        qpa[8][0] = "Correct way to allocate 1D array?";
        qpa[8][1] = "int[size] arr=new int[]";
        qpa[8][2] = "int arr[size]=new int[]";
        qpa[8][3] = "int[size] arr=new int[size]";
        qpa[8][4] = "int[] arr=new int[size]";

        qpa[9][0] = "Correct way to allocate 2D array?";
        qpa[9][1] = "int[size][] arr=new int[][]";
        qpa[9][2] = "int arr=new int[rows][cols]";
        qpa[9][3] = "int arr[rows][]=new int[rows][cols]";
        qpa[9][4] = "int[][] arr=new int[rows][cols]";

        // Correct answers array
        qca = new String[10][2];
        qca[0][1] = "java JavaProgram";
        qca[1][1] = "It is used to print text on the screen with the line break.";
        qca[2][1] = "char c=(char)System.in.read()";
        qca[3][1] = "2";
        qca[4][1] = "int x";
        qca[5][1] = "int num = Integer.parseInt(str_num)";
        qca[6][1] = "0";
        qca[7][1] = "Using break";
        qca[8][1] = "int[] arr=new int[size]";
        qca[9][1] = "int[][] arr=new int[rows][cols]";

        // Questions for reference
        for (int i = 0; i < 10; i++) {
            qca[i][0] = qpa[i][0];
        }

        map = new HashMap<>();
    }

    // Reset quiz
    public void reset() {
        qaid = 0;
        map.clear();
        readqa(qaid);
        btnext.setText("Next");
    }

    // Count correct answers
    public int calCorrectAnswer() {
        int count = 0;
        for (int qid = 0; qid < qca.length; qid++) {
            if (qca[qid][1].equals(map.get(qid))) {
                count++;
            }
        }
        return count;
    }

    // Inner class to show report
    public class Report extends JFrame {
        Report() {
            setTitle("Answers");
            setSize(850, 550);
            setBackground(Color.WHITE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    dispose();
                    reset();
                }
            });
            Draw d = new Draw();
            add(d);
            setVisible(true);
        }

        // Canvas for drawing the answers and score
        class Draw extends Canvas {
            public void paint(Graphics g) {
                int x = 10, y = 20;
                for (int i = 0; i < qca.length; i++) {
                    g.setFont(new Font("Arial", Font.BOLD, 12));
                    g.setColor(Color.BLACK);
                    g.drawString((i + 1) + ". " + qca[i][0], x, y);
                    g.setColor(Color.BLUE);
                    g.drawString("Correct Answer: " + qca[i][1], x, y + 15);
                    g.setColor(Color.RED);
                    g.drawString("Your Answer: " + map.get(i), x, y + 30);
                    y += 60;
                    if (y > 500) {
                        y = 20;
                        x += 400;
                    }
                }
                g.setColor(Color.MAGENTA);
                g.setFont(new Font("Arial", Font.BOLD, 14));
                g.drawString("Total Correct Answers: " + calCorrectAnswer(), 300, 500);
            }
        }
    }
}

// Main class to run the application
public class QuizProgram {
    public static void main(String[] args) {
        new Quiz();
    }
}
