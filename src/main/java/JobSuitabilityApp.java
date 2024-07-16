import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class JobSuitabilityApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Job Suitability Checker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 1002);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 5, 3, 5);

        String[] jobs = {"UPR B 1 ROTA DAGITMA", "UB MIX 1 ROTA ", "UPR B 2 ROTA DAGITMA", "UB MIX 2 ROTA ", "SB ROTA DAGITMA",
                "UPR B DAISHA TRANSFER LH", "FR SM DIRECT DELIVERY ROTA", "LOCAL EXC TW 2", "UB III ZONE 1 PARCA DIZME",
                "RONOJI SET TRANSFER RH", "RONOJI SET TRANSFER LH", "LOCAL EXC TW 1", "LOKAL FL 1", "LOKAL FL 2", "LOKAL FL 3",
                "PLASTIK DOCK VE KONTEYNER OPR", "TTTI DOCK OPR", "KOWAKE PLASTIK PALET BESLEME", "EXC PALET DAGITIM-1",
                "EXC PALET DAGITIM-2", "KUTU PARÇA DAGITIM & CKD", "KOWAKE EXC. PALET DAGITIM", "STRLS TEMZ VE YUK OPR", "SB ROTA YUKLEME",
                "UPR/B 1 ve UPR/B 2. ROTA YUKLEME", "P-LANE DAGITIM ve EXPORT PAKETLEME", "ARAC BOSALTMA VE YUKLEME OPERASYONU",
                "LOCAL FORKLIFT 1", "LOCAL FORKLIFT 2", "PRES FORKLIFT 1", "PRES FORKLIFT 2", "LH-290-560 SMALL LOT ROTA",
                "RH-290-560 SMALL LOT ROTA", "EXC DOLU PALET DAGITIM TW 1 ", "EXC DOLU PALET DAGITIM TW 2", "EXC DOLU PALET DAGITIM TW 3",
                "EXC DOLU PALET DAGITIM TW 4", "EXC BOS PALET TOPLAMA", "PW ROTA & EXC. BOS PALET TOPLAMA", "LOCAL SET DOLLY TW"
        };
        JComboBox<String> jobComboBox = new JComboBox<>(jobs);
        jobComboBox.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(new JLabel("İş seçiniz:"), gbc);
        gbc.gridy = 1;
        mainPanel.add(jobComboBox, gbc);

        gbc.gridwidth = 1;
        String[] options = {"Yok", "Düşük", "Orta", "Yüksek"};
        String[] questions = {
                "Boyun öne esneme", "Boyun geriye esneme", "Boyun yana esneme", "Boyun çevirme",
                "El ile uzanma", "Dirseğin tam açılması", "Baş üzerinde çalışma",
                "Dirsek omuz seviyesinden yukarı çalışma", "Ön kolun çevrilmesi", "El ile kavrama",
                "İki parmak ile sıkarak kavrama-itme-çekme / Baş parmak ile itme",
                "Bilek bükme(yukarı ve aşağı)", "Bileğin yanal hareketi", "Statik konumda itme-çekme",
                "Hareket halindeyken itme-çekme", "Belden eğilme", "Belden eğilme ve uzanma",
                "Belden yana esneme", "Yük kaldırma(Elleçleme)", "Beli çevirme", "Çökelme-Diz çökme"
        };

        JComboBox<String>[] questionComboBoxes = new JComboBox[21];

        int[][] groups = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11, 12}, {13, 14}, {15, 16, 17, 18, 19, 20}};
        Color[] colors = {new Color(255, 230, 230), new Color(230, 255, 230), new Color(230, 230, 255), new Color(255, 255, 230), new Color(230, 255, 255)};
        String[] categories = {"BOYUN", "OMUZ", "BİLEK", "BEL/BOYUN/OMUZ/BİLEK", "BEL", "DİZ"};
        for (int i = 0; i < groups.length; i++) {
            JPanel groupPanel = new JPanel();
            groupPanel.setLayout(new GridBagLayout());
            groupPanel.setBackground(colors[i]);
            groupPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), categories[i], TitledBorder.CENTER, TitledBorder.TOP));

            GridBagConstraints groupGbc = new GridBagConstraints();
            groupGbc.fill = GridBagConstraints.HORIZONTAL;
            groupGbc.insets = new Insets(5, 5, 5, 5);
            groupGbc.gridx = 0;

            for (int j : groups[i]) {
                groupGbc.gridy = j;
                groupPanel.add(new JLabel(questions[j]), groupGbc);
                questionComboBoxes[j] = new JComboBox<>(options);
                questionComboBoxes[j].setPreferredSize(new Dimension(100, 25));
                groupGbc.gridx = 1;
                groupPanel.add(questionComboBoxes[j], groupGbc);
                groupGbc.gridx = 0;
            }

            gbc.gridy += groups[i].length + 1;
            mainPanel.add(groupPanel, gbc);
        }

        JButton submitButton = new JButton("Gönder");
        submitButton.setPreferredSize(new Dimension(100, 25));
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(submitButton, gbc);

        submitButton.addActionListener(e -> {
            String selectedJob = (String) jobComboBox.getSelectedItem();
            int[] scores = new int[21];
            for (int i = 0; i < 21; i++) {
                String answer = (String) questionComboBoxes[i].getSelectedItem();
                switch (answer) {
                    case "Yok":
                        scores[i] = 0;
                        break;
                    case "Düşük", "Orta", "Yüksek":
                        scores[i] = 1;
                        break;
                    case null:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + answer);
                }
            }

            int totalScore = 0;
            for (int score : scores) {
                totalScore += score;
            }

            String result = "";
            switch (selectedJob) {
                case "UPR B 1 ROTA DAGITMA":
                    result = (totalScore <= 5) ? "\"UPR B 1 ROTA DAGITMA\" işi için Düşük Riskli" :
                            (totalScore < 10) ? "\"UPR B 1 ROTA DAGITMA\" işi için Orta Riskli" :
                                    "\"UPR B 1 ROTA DAGITMA\" işi için Yüksek Riskli";
                    break;
                case "UB MIX 1 ROTA":
                    result = (totalScore <= 6) ? "\"UB MIX 1 ROTA\" işi için Düşük Riskli" :
                            (totalScore < 12) ? "\"UB MIX 1 ROTA\" işi için Orta Riskli" :
                                    "\"UB MIX 1 ROTA\" işi için Yüksek Riskli";
                    break;
                case "UPR B 2 ROTA DAGITMA":
                    result = (totalScore <= 4) ? "\"UPR B 2 ROTA DAGITMA\" işi için Düşük Riskli" :
                            (totalScore < 9) ? "\"UPR B 2 ROTA DAGITMA\" işi için Orta Riskli" :
                                    "\"UPR B 2 ROTA DAGITMA\" işi için Yüksek Riskli";
                    break;
                case "UB MIX 2 ROTA":
                    result = (totalScore <= 7) ? "\"UB MIX 2 ROTA\" işi için Düşük Riskli" :
                            (totalScore < 13) ? "\"UB MIX 2 ROTA\" işi için Orta Riskli" :
                                    "\"UB MIX 2 ROTA\" işi için Yüksek Riskli";
                    break;
                case "SB ROTA DAGITMA":
                    result = (totalScore <= 5) ? "\"SB ROTA DAGITMA\" işi için Düşük Riskli" :
                            (totalScore < 11) ? "\"SB ROTA DAGITMA\" işi için Orta Riskli" :
                                    "\"SB ROTA DAGITMA\" işi için Yüksek Riskli";
                    break;
                case "UPR B DAISHA TRANSFER LH":
                    result = (totalScore <= 6) ? "\"UPR B DAISHA TRANSFER LH\" işi için Düşük Riskli" :
                            (totalScore < 12) ? "\"UPR B DAISHA TRANSFER LH\" işi için Orta Riskli" :
                                    "\"UPR B DAISHA TRANSFER LH\" işi için Yüksek Riskli";
                    break;
                case "FR SM DIRECT DELIVERY ROTA":
                    result = (totalScore <= 7) ? "\"FR SM DIRECT DELIVERY ROTA\" işi için Düşük Riskli" :
                            (totalScore < 13) ? "\"FR SM DIRECT DELIVERY ROTA\" işi için Orta Riskli" :
                                    "\"FR SM DIRECT DELIVERY ROTA\" işi için Yüksek Riskli";
                    break;
                case "LOCAL EXC TW 2":
                    result = (totalScore <= 6) ? "\"LOCAL EXC TW 2\" işi için Düşük Riskli" :
                            (totalScore < 11) ? "\"LOCAL EXC TW 2\" işi için Orta Riskli" :
                                    "\"LOCAL EXC TW 2\" işi için Yüksek Riskli";
                    break;
                case "UB III ZONE 1 PARCA DIZME":
                    result = (totalScore <= 5) ? "\"UB III ZONE 1 PARCA DIZME\" işi için Düşük Riskli" :
                            (totalScore < 10) ? "\"UB III ZONE 1 PARCA DIZME\" işi için Orta Riskli" :
                                    "\"UB III ZONE 1 PARCA DIZME\" işi için Yüksek Riskli";
                    break;
                case "RONOJI SET TRANSFER RH":
                    result = (totalScore <= 6) ? "\"RONOJI SET TRANSFER RH\" işi için Düşük Riskli" :
                            (totalScore < 11) ? "\"RONOJI SET TRANSFER RH\" işi için Orta Riskli" :
                                    "\"RONOJI SET TRANSFER RH\" işi için Yüksek Riskli";
                    break;
                case "RONOJI SET TRANSFER LH":
                    result = (totalScore <= 5) ? "\"RONOJI SET TRANSFER LH\" işi için Düşük Riskli" :
                            (totalScore < 12) ? "\"RONOJI SET TRANSFER LH\" işi için Orta Riskli" :
                                    "\"RONOJI SET TRANSFER LH\" işi için Yüksek Riskli";
                    break;
                case "LOCAL EXC TW 1":
                    result = (totalScore <= 3) ? "\"LOCAL EXC TW 1\" işi için Düşük Riskli" :
                            (totalScore < 9) ? "\"LOCAL EXC TW 1\" işi için Orta Riskli" :
                                    "\"LOCAL EXC TW 1\" işi için Yüksek Riskli";
                    break;
                case "LOKAL FL 1":
                    result = (totalScore <= 5) ? "\"LOKAL FL 1\" işi için Düşük Riskli" :
                            (totalScore < 11) ? "\"LOKAL FL 1\" işi için Orta Riskli" :
                                    "\"LOKAL FL 1\" işi için Yüksek Riskli";
                    break;
                case "LOKAL FL 2":
                    result = (totalScore <= 2) ? "\"LOKAL FL 2\" işi için Düşük Riskli" :
                            (totalScore < 8) ? "\"LOKAL FL 2\" işi için Orta Riskli" :
                                    "\"LOKAL FL 2\" işi için Yüksek Riskli";
                    break;
                case "LOKAL FL 3":
                    result = (totalScore <= 4) ? "\"LOKAL FL 3\" işi için Düşük Riskli" :
                            (totalScore < 10) ? "\"LOKAL FL 3\" işi için Orta Riskli" :
                                    "\"LOKAL FL 3\" işi için Yüksek Riskli";
                    break;
                case "PLASTIK DOCK VE KONTEYNER OPR":
                    result = (totalScore <= 3) ? "\"PLASTIK DOCK VE KONTEYNER OPR\" işi için Düşük Riskli" :
                            (totalScore < 9) ? "\"PLASTIK DOCK VE KONTEYNER OPR\" işi için Orta Riskli" :
                                    "\"PLASTIK DOCK VE KONTEYNER OPR\" işi için Yüksek Riskli";
                    break;
                case "TTTI DOCK OPR":
                    result = (totalScore <= 5) ? "\"TTTI DOCK OPR\" işi için Düşük Riskli" :
                            (totalScore < 12) ? "\"TTTI DOCK OPR\" işi için Orta Riskli" :
                                    "\"TTTI DOCK OPR\" işi için Yüksek Riskli";
                    break;
                case "KOWAKE PLASTIK PALET BESLEME":
                    result = (totalScore <= 2) ? "\"KOWAKE PLASTIK PALET BESLEME\" işi için Düşük Riskli" :
                            (totalScore < 8) ? "\"KOWAKE PLASTIK PALET BESLEME\" işi için Orta Riskli" :
                                    "\"KOWAKE PLASTIK PALET BESLEME\" işi için Yüksek Riskli";
                    break;
                case "EXC PALET DAGITIM-1":
                    result = (totalScore <= 4) ? "\"EXC PALET DAGITIM-1\" işi için Düşük Riskli" :
                            (totalScore < 10) ? "\"EXC PALET DAGITIM-1\" işi için Orta Riskli" :
                                    "\"EXC PALET DAGITIM-1\" işi için Yüksek Riskli";
                    break;
                case "EXC PALET DAGITIM-2":
                    result = (totalScore <= 3) ? "\"EXC PALET DAGITIM-2\" işi için Düşük Riskli" :
                            (totalScore < 9) ? "\"EXC PALET DAGITIM-2\" işi için Orta Riskli" :
                                    "\"EXC PALET DAGITIM-2\" işi için Yüksek Riskli";
                    break;
                case "KUTU PARÇA DAGITIM & CKD":
                    result = (totalScore <= 4) ? "\"KUTU PARÇA DAGITIM & CKD\" işi için Düşük Riskli" :
                            (totalScore < 11) ? "\"KUTU PARÇA DAGITIM & CKD\" işi için Orta Riskli" :
                                    "\"KUTU PARÇA DAGITIM & CKD\" işi için Yüksek Riskli";
                    break;
                case "KOWAKE EXC. PALET DAGITIM":
                    result = (totalScore <= 5) ? "\"KOWAKE EXC. PALET DAGITIM\" işi için Düşük Riskli" :
                            (totalScore < 9) ? "\"KOWAKE EXC. PALET DAGITIM\" işi için Orta Riskli" :
                                    "\"KOWAKE EXC. PALET DAGITIM\" işi için Yüksek Riskli";
                    break;
                case "STRLS TEMZ VE YUK OPR":
                    result = (totalScore <= 7) ? "\"STRLS TEMZ VE YUK OPR\" işi için Düşük Riskli" :
                            (totalScore < 12) ? "\"STRLS TEMZ VE YUK OPR\" işi için Orta Riskli" :
                                    "\"STRLS TEMZ VE YUK OPR\" işi için Yüksek Riskli";
                    break;
                case "SB ROTA YUKLEME":
                    result = (totalScore <= 6) ? "\"SB ROTA YUKLEME\" işi için Düşük Riskli" :
                            (totalScore < 11) ? "\"SB ROTA YUKLEME\" işi için Orta Riskli" :
                                    "\"SB ROTA YUKLEME\" işi için Yüksek Riskli";
                    break;
                case "UPR/B 1 ve UPR/B 2. ROTA YUKLEME":
                    result = (totalScore <= 4) ? "\"UPR/B 1 ve UPR/B 2. ROTA YUKLEME\" işi için Düşük Riskli" :
                            (totalScore < 9) ? "\"UPR/B 1 ve UPR/B 2. ROTA YUKLEME\" işi için Orta Riskli" :
                                    "\"UPR/B 1 ve UPR/B 2. ROTA YUKLEME\" işi için Yüksek Riskli";
                    break;
                case "P-LANE DAGITIM ve EXPORT PAKETLEME":
                    result = (totalScore <= 5) ? "\"P-LANE DAGITIM ve EXPORT PAKETLEME\" işi için Düşük Riskli" :
                            (totalScore < 10) ? "\"P-LANE DAGITIM ve EXPORT PAKETLEME\" işi için Orta Riskli" :
                                    "\"P-LANE DAGITIM ve EXPORT PAKETLEME\" işi için Yüksek Riskli";
                    break;
                case "ARAC BOSALTMA VE YUKLEME OPERASYONU":
                    result = (totalScore <= 6) ? "\"ARAC BOSALTMA VE YUKLEME OPERASYONU\" işi için Düşük Riskli" :
                            (totalScore < 11) ? "\"ARAC BOSALTMA VE YUKLEME OPERASYONU\" işi için Orta Riskli" :
                                    "\"ARAC BOSALTMA VE YUKLEME OPERASYONU\" işi için Yüksek Riskli";
                    break;
                case "LOCAL FORKLIFT 1":
                    result = (totalScore <= 4) ? "\"LOCAL FORKLIFT 1\" işi için Düşük Riskli" :
                            (totalScore < 9) ? "\"LOCAL FORKLIFT 1\" işi için Orta Riskli" :
                                    "\"LOCAL FORKLIFT 1\" işi için Yüksek Riskli";
                    break;
                case "LOCAL FORKLIFT 2":
                    result = (totalScore <= 6) ? "\"LOCAL FORKLIFT 2\" işi için Düşük Riskli" :
                            (totalScore < 11) ? "\"LOCAL FORKLIFT 2\" işi için Orta Riskli" :
                                    "\"LOCAL FORKLIFT 2\" işi için Yüksek Riskli";
                    break;
                case "PRES FORKLIFT 1":
                    result = (totalScore <= 5) ? "\"PRES FORKLIFT 1\" işi için Düşük Riskli" :
                            (totalScore < 10) ? "\"PRES FORKLIFT 1\" işi için Orta Riskli" :
                                    "\"PRES FORKLIFT 1\" işi için Yüksek Riskli";
                    break;
                case "PRES FORKLIFT 2":
                    result = (totalScore <= 7) ? "\"PRES FORKLIFT 2\" işi için Düşük Riskli" :
                            (totalScore < 12) ? "\"PRES FORKLIFT 2\" işi için Orta Riskli" :
                                    "\"PRES FORKLIFT 2\" işi için Yüksek Riskli";
                    break;
                case "LH-290-560 SMALL LOT ROTA":
                    result = (totalScore <= 6) ? "\"LH-290-560 SMALL LOT ROTA\" işi için Düşük Riskli" :
                            (totalScore < 11) ? "\"LH-290-560 SMALL LOT ROTA\" işi için Orta Riskli" :
                                    "\"LH-290-560 SMALL LOT ROTA\" işi için Yüksek Riskli";
                    break;
                case "RH-290-560 SMALL LOT ROTA":
                    result = (totalScore <= 5) ? "\"RH-290-560 SMALL LOT ROTA\" işi için Düşük Riskli" :
                            (totalScore < 10) ? "\"RH-290-560 SMALL LOT ROTA\" işi için Orta Riskli" :
                                    "\"RH-290-560 SMALL LOT ROTA\" işi için Yüksek Riskli";
                    break;
                case "EXC DOLU PALET DAGITIM TW 1":
                    result = (totalScore <= 6) ? "\"EXC DOLU PALET DAGITIM TW 1\" işi için Düşük Riskli" :
                            (totalScore < 11) ? "\"EXC DOLU PALET DAGITIM TW 1\" işi için Orta Riskli" :
                                    "\"EXC DOLU PALET DAGITIM TW 1\" işi için Yüksek Riskli";
                    break;
                case "EXC DOLU PALET DAGITIM TW 2":
                    result = (totalScore <= 5) ? "\"EXC DOLU PALET DAGITIM TW 2\" işi için Düşük Riskli" :
                            (totalScore < 10) ? "\"EXC DOLU PALET DAGITIM TW 2\" işi için Orta Riskli" :
                                    "\"EXC DOLU PALET DAGITIM TW 2\" işi için Yüksek Riskli";
                    break;
                case "EXC DOLU PALET DAGITIM TW 3":
                    result = (totalScore <= 7) ? "\"EXC DOLU PALET DAGITIM TW 3\" işi için Düşük Riskli" :
                            (totalScore < 12) ? "\"EXC DOLU PALET DAGITIM TW 3\" işi için Orta Riskli" :
                                    "\"EXC DOLU PALET DAGITIM TW 3\" işi için Yüksek Riskli";
                    break;
                case "EXC DOLU PALET DAGITIM TW 4":
                    result = (totalScore <= 6) ? "\"EXC DOLU PALET DAGITIM TW 4\" işi için Düşük Riskli" :
                            (totalScore < 11) ? "\"EXC DOLU PALET DAGITIM TW 4\" işi için Orta Riskli" :
                                    "\"EXC DOLU PALET DAGITIM TW 4\" işi için Yüksek Riskli";
                    break;
                case "EXC BOS PALET TOPLAMA":
                    result = (totalScore <= 5) ? "\"EXC BOS PALET TOPLAMA\" işi için Düşük Riskli" :
                            (totalScore < 10) ? "\"EXC BOS PALET TOPLAMA\" işi için Orta Riskli" :
                                    "\"EXC BOS PALET TOPLAMA\" işi için Yüksek Riskli";
                    break;
                case "PW ROTA & EXC. BOS PALET TOPLAMA":
                    result = (totalScore <= 4) ? "\"PW ROTA & EXC. BOS PALET TOPLAMA\" işi için Düşük Riskli" :
                            (totalScore < 9) ? "\"PW ROTA & EXC. BOS PALET TOPLAMA\" işi için Orta Riskli" :
                                    "\"PW ROTA & EXC. BOS PALET TOPLAMA\" işi için Yüksek Riskli";
                    break;
                case "LOCAL SET DOLLY TW":
                    result = (totalScore <= 6) ? "\"LOCAL SET DOLLY TW\" işi için Düşük Riskli" :
                            (totalScore < 11) ? "\"LOCAL SET DOLLY TW\" işi için Orta Riskli" :
                                    "\"LOCAL SET DOLLY TW\" işi için Yüksek Riskli";
                    break;
                case null:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + selectedJob);
            }

            JOptionPane.showMessageDialog(frame, result);
        });

        frame.setVisible(true);
    }
}
