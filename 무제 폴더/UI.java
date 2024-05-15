package com.multi.miniproject.common.view;

import com.multi.miniproject.member.model.dao.*;
import com.multi.miniproject.member.model.dto.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class UI {
    JFrame f = new JFrame();
    static String loginUser = null;


    public void p01() {
//JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p01 : 메인 페이지");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
//        JButton b0 = new JButton("<-뒤로가기");   // 첫페이지
        JButton b1 = new JButton("로그인: p02()으로 이동");
        JButton b2 = new JButton("회원가입: p01_1()으로 이동");

//        f.add(b0);
        f.add(b1);
        f.add(b2);

//        b0.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                p01();
//            }
//        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p02();
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p01_1();
            }
        }); //b2.addActionListener

        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void p01_1() {            //회원가입
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("회원가입 페이지");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p01_1 : 회원가입  ");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");

        JLabel l2 = new JLabel("아이디");
        JTextField t2 = new JTextField(20); //
        JButton b1 = new JButton("중복확인");

        JLabel l3 = new JLabel("비밀번호");
        JTextField t3 = new JTextField(25); // 10은 글자수
        JLabel l4 = new JLabel("비밀번호 확인");
        JTextField t4 = new JTextField(25); // 10은 글자수
        JLabel l5 = new JLabel("이름");
        JTextField t5 = new JTextField(15); // 10은 글자수
        JLabel l6 = new JLabel("이메일");
        JTextField t6 = new JTextField(10); // 10은 글자수
        JTextField t7 = new JTextField(10); // 10은 글자수 //'직접입력'일시 잠금해제 하는 기능 구현 필요.

        //combobox2: 이메일 도메인
        String[] g2 = {"@naver.com", "@gmail.com", "직접입력"};
        JComboBox combo2 = new JComboBox(g2);
        JButton b2 = new JButton("등록: p02()로 이동");


        f.add(b0);
        f.add(l2);
        f.add(t2);

        f.add(b1);
        f.add(l3);
        f.add(t3);
        f.add(l4);
        f.add(t4);
        f.add(l5);
        f.add(t5);
        f.add(l6);
        f.add(t6);
        f.add(t7);
        f.add(combo2);

        f.add(b2);


        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p01();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() { //중복확인
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = t2.getText();

                MemberDao memberDao = new MemberDao();
                if (memberDao.idCheck(id)) {
                    JOptionPane.showMessageDialog(f, "이미 사용중인 아이디입니다.");
                } else {
                    JOptionPane.showMessageDialog(f, "사용 가능한 아이디입니다.");

                }
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() { //회원가입 버튼
            @Override
            public void actionPerformed(ActionEvent e) {

                String id = t2.getText();
                String pw = t3.getText();
                String pw2 = t4.getText();
                String name = t5.getText();
                String emailID = t6.getText();
                String emailSite;


                String selectedValue = combo2.getSelectedItem().toString();
                if (selectedValue.equals("직접입력")) {
                    emailSite = t7.getText();
                } else {
                    t7.setText("");
                    emailSite = selectedValue;
                }

                MemberDao memberDao = new MemberDao();
                boolean startsWithAt = emailSite.startsWith("@");
                if(id.equals("")||pw.equals("")||name.equals("")||emailID.equals("")){
                    JOptionPane.showMessageDialog(f, "입력되지 않은 값이 존재합니다.");
                }else if (pw.equals(pw2) && memberDao.idCheck(id) == false && startsWithAt == true) {
                    MemberDto memberDto = new MemberDto(id, pw, name, emailID, emailSite);
                    int result = memberDao.joinMember(memberDto);
                    if (result == 1) {
                        JOptionPane.showMessageDialog(f, "회원가입 완료. 로그인 해주세요.");
                        p02();
                    } else {
                        JOptionPane.showMessageDialog(f, "회원가입 실패. 재시도 해주세요.");
                    }

                } else if (!pw.equals(pw2)) {
                    JOptionPane.showMessageDialog(f, "비밀번호 값이 일치하지 않습니다.");
                } else if (startsWithAt == false) {
                    JOptionPane.showMessageDialog(f, "올바르지 않은 이메일 형식입니다(@필수)");
                } else {
                    JOptionPane.showMessageDialog(f, "중복된 아이디입니다.");
                }

            }
        }); //b2.addActionListener
        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void p02() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p02 : 로그인 페이지");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");

        JLabel l2 = new JLabel("아이디");
        JTextField t2 = new JTextField(10); //
        JLabel l3 = new JLabel("비밀번호");
        JTextField t3 = new JTextField(10); // 10은 글자수

        JButton b1 = new JButton("로그인: p03()으로 이동");
        JButton b2 = new JButton("비밀번호 찾기");
        JButton b3 = new JButton("회원가입: p01_1()으로 이동");

        f.add(b0);
        f.add(l2);
        f.add(t2);
        f.add(l3);
        f.add(t3);
        f.add(b1);
        f.add(b2);
        f.add(b3);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p01();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {     //로그인
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = t2.getText();
                String pw = t3.getText();

                MemberDto memberDto = new MemberDto();
                memberDto.setId(id);
                memberDto.setPw(pw);

                MemberDao memberDao = new MemberDao();
                boolean result = memberDao.login(id, pw);

                if (result) {
                    JOptionPane.showMessageDialog(f, "로그인 성공");
                    loginUser = memberDao.selectOne(id).getMemberNum();
                    p03();
                } else if (id.equals("") || pw.equals("")) {
                    JOptionPane.showMessageDialog(f, "입력되지 않은 값이 있습니다");
                } else if (memberDao.idCheck(id) == false) {
                    JOptionPane.showMessageDialog(f, "존재하지 않는 아이디 입니다.");
                } else {
                    JOptionPane.showMessageDialog(f, "아이디와 비밀번호가 일치하지 않습니다.");
                }


            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog("아이디를 입력하세요");
                MemberDao memberDao = new MemberDao();
                if (memberDao.idCheck(id)) {
                    String name = JOptionPane.showInputDialog("이름을 입력하세요");
                    boolean result = memberDao.findPw(id, name);
                    if (result == true) {
                        JOptionPane.showMessageDialog(f, "ID : " + id + " PW : " + (memberDao.selectOne(id)).getPw());
                    } else {
                        JOptionPane.showMessageDialog(f, "일치하는 회원정보가 없습니다.");
                    }
                } else {
                    JOptionPane.showMessageDialog(f, "존재하지 않는 아이디 입니다.");
                }

            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p01_1();
            }
        }); //b3.addActionListener
        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } //p02() 로그인페이지

    public void p03() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.YELLOW);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p03 : 메뉴");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JButton b1 = new JButton("마이 필터");
        JButton b2 = new JButton("상품 조회");
        JButton b3 = new JButton("리뷰");
        JButton b4 = new JButton("마이 페이지");

        b1.setBackground(Color.CYAN);
        b1.setOpaque(true);
        b2.setBackground(Color.CYAN);
        b2.setOpaque(true);
        b3.setBackground(Color.YELLOW);
        b3.setOpaque(true);
        b4.setBackground(Color.YELLOW);
        b4.setOpaque(true);

        f.add(b0);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p02();
                loginUser = null;
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p07();
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06();
            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReviewPage reviewPage = new ReviewPage();
                reviewPage.p05();
                f.setVisible(false);
//                    p05();
            }
        }); //b3.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mypage mypage=new Mypage();
                mypage.p04();
            }
        }); //b4.addActionListener

        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } //p03() : 메뉴

//    public void p04() {
//
//        MemberDao dao = new MemberDao();
//
//        //JFrame 정의
////        f = new JFrame();
//        f.getContentPane().removeAll();
//        f.repaint();
//        f.setSize(400, 600);
//        f.setTitle("첫화면");
//        f.getContentPane().setBackground(Color.YELLOW);
//
//        // FlowLayout ?
//        FlowLayout flow = new FlowLayout();
//        f.setLayout(flow);
//
//        //페이지제목
//        JLabel l1 = new JLabel("p04 : 마이페이지");
//        Font font = new Font("맑은 고딕", Font.BOLD, 30);
//        l1.setFont(font);
//        f.add(l1);
//
//        /////////////////////////////////////////////////////////
//        JButton b0 = new JButton("<-뒤로가기");
//        JLabel l2 = new JLabel("이름 " + dao.loginUser(loginUser).getName());
//        JLabel l3 = new JLabel("아이디(이메일)" + dao.loginUser(loginUser).getId() +
//                "(" + dao.loginUser(loginUser).getEmail() + ")");
//        //이미지
//        JLabel img1 = new JLabel("이미지");
////        img1.setIcon(new ImageIcon("images/img.jpg"));
//        //
//        JLabel l4 = new JLabel("//계정 관리");
//        JButton b1 = new JButton("프로필 재설정");
//        JButton b2 = new JButton("회원탈퇴");
//        JButton b3 = new JButton("로그아웃");
//
//        f.add(b0);
//        f.add(l2);
//        f.add(l3);
//        f.add(img1);
//        f.add(l4);
//        f.add(b1);
//        f.add(b2);
//        f.add(b3);
//
//        b0.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                p03();
//            }
//        }); //b0.addActionListener
//
//        b1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                p04_1();
//            }
//        }); //b1.addActionListener
//
//        b2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                p04_2();
//            }
//        }); //b2.addActionListener
//
//        b3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(f, "로그아웃되었습니다.");
//                loginUser = null;
//                p01();
//            }
//        }); //b3.addActionListener
//        /////////////////////////////////////////
//        //JFrame Visible처리
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setVisible(true);
//    } //p04() : 마이페이지
//
//    public void p04_1() {
//
//        MemberDao dao = new MemberDao();
//
//        //JFrame 정의
////        f = new JFrame();
//        f.getContentPane().removeAll();
//        f.repaint();
//        f.setSize(400, 600);
//        f.setTitle("첫화면");
//        f.getContentPane().setBackground(Color.YELLOW);
//
//        // FlowLayout ?
//        FlowLayout flow = new FlowLayout();
//        f.setLayout(flow);
//
//        //페이지제목
//        JLabel l1 = new JLabel("p04_1 : 프로필 재설정");
//        Font font = new Font("맑은 고딕", Font.BOLD, 30);
//        l1.setFont(font);
//        f.add(l1);
//
//        /////////////////////////////////////////////////////////
//        JButton b0 = new JButton("<-뒤로가기");
//        JLabel l2 = new JLabel("아이디");
//        JLabel l21 = new JLabel(dao.loginUser(loginUser).getId()); //수정불가하므로
//        JLabel l3 = new JLabel("현재 비밀번호");
//        JTextField t3 = new JTextField(30); // 10은 글자수
//        JLabel l4 = new JLabel("변경 비밀번호");
//        JTextField t4 = new JTextField(30); // 10은 글자수
//        JLabel l5 = new JLabel("이름");
//        JTextField t5 = new JTextField(10); // 10은 글자수
//        t5.setText(dao.loginUser(loginUser).getName());
//        JLabel l6 = new JLabel("이메일");
//        JTextField t6 = new JTextField(15); // 10은 글자수
//        t6.setText(dao.loginUser(loginUser).getEmailID());
//        JTextField t7 = new JTextField(15);
//
//
//        //combobox2: 이메일 도메인
//        ArrayList<String> g2 = new ArrayList<>();
//        g2.add("@naver.com");
//        g2.add("@gmail.com");
//        g2.add("직접입력");
//        JComboBox<String> combo2 = new JComboBox<>(g2.toArray(new String[0]));
//
//
//        if (g2.contains(dao.loginUser(loginUser).getEmailSite())) {
//            // 작성불가/jbox 일치시키기
//        } else {
//            t7.setText(dao.loginUser(loginUser).getEmailSite());
//        }
//
//
//        JButton b1 = new JButton("수정");
//
//        f.add(b0);
//        f.add(l2);
//        f.add(l21);
//        f.add(l3);
//        f.add(t3);
//        f.add(l4);
//        f.add(t4);
//        f.add(l5);
//        f.add(t5);
//        f.add(l6);
//        f.add(t6);
//        f.add(t7);
//        f.add(combo2);
//        f.add(b1);
//
//        b0.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                p04();
//            }
//        }); //b0.addActionListener
//
//        b1.addActionListener(new ActionListener() { // 수정
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String pw = t3.getText();
//                String pwNew = t4.getText();
//                String name = t5.getText();
//                String emailID = t6.getText();
//                String emailSite;
//
//                String selectedValue = combo2.getSelectedItem().toString();
//                if (selectedValue.equals("직접입력")) {
//                    emailSite = t7.getText();
//                } else {
//                    emailSite = selectedValue;
//                }
//                if (dao.loginUser(loginUser).getPw().equals(pw)) {
//
//                    MemberDto memberDto = new MemberDto(l21.getText(), pwNew, name, emailID, emailSite);
//                    MemberDao memberDao = new MemberDao();
//                    int result = memberDao.updateUser(memberDto);
//
//                    if (result == 1) {
//                        JOptionPane.showMessageDialog(f, "수정되었습니다.");
//                        p04();
//                    } else {
//                        JOptionPane.showMessageDialog(f, "업데이트 실패. 다시 시도해주세요.");
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(f, "비밀번호가 올바르지 않습니다");
//                }
//
//            }
//        }); //b1.addActionListener
//        /////////////////////////////////////////
//
//        //JFrame Visible처리
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setVisible(true);
//    } // p04_1() : 프로필 재설정
//
//    public void p04_2() {
//        //JFrame 정의
////        f = new JFrame();
//
//        MemberDao dao = new MemberDao();
//
//        Random r = new Random();
//        StringBuilder rString = new StringBuilder();
//        for (int i = 0; i < 6; i++) {
//            int digit = r.nextInt(16); // 0부터 15 사이의 난수 생성
//            rString.append(Integer.toHexString(digit)); // 16진수로 변환하여 추가
//        }
//
//
//        f.getContentPane().removeAll();
//        f.repaint();
//        f.setSize(400, 600);
//        f.setTitle("첫화면");
//        f.getContentPane().setBackground(Color.YELLOW);
//
//        // FlowLayout ?
//        FlowLayout flow = new FlowLayout();
//        f.setLayout(flow);
//
//        //페이지제목
//        JLabel l1 = new JLabel("p04_2 : 회원탈퇴");
//        Font font = new Font("맑은 고딕", Font.BOLD, 30);
//        l1.setFont(font);
//        f.add(l1);
//        /////////////////////////////////////////////////////////
//        JButton b0 = new JButton("<-뒤로가기");
//        JLabel l2 = new JLabel("아이디");
//        JLabel l21 = new JLabel(dao.loginUser(loginUser).getId()); //수정불가하므로
//        JLabel l3 = new JLabel("비밀번호");
//        JTextField t3 = new JTextField(30); // 10은 글자수
//        JLabel l4 = new JLabel("자동 입력방지");
//        JLabel l41 = new JLabel(String.format(rString.toString()));
//        JButton b1 = new JButton("새로고침");
//        JTextField t4 = new JTextField(10); // 10은 글자수
//
//        //checkbox 예제
//        JLabel l5 = new JLabel("주의");
//        JLabel l6 = new JLabel("탈퇴시 보유하고 있던 모든 혜택이 사라집니다. 이후 같은 아이디로 재가입이 불가합니다.");
//        JCheckBox cb1 = new JCheckBox("주의사항을 확인했으며 이에 동의합니다.");
//
//        JButton b2 = new JButton("탈퇴");
//
//        f.add(b0);
//        f.add(l2);
//        f.add(l21);
//        f.add(l3);
//        f.add(t3);
//        f.add(l4);
//        f.add(l41);
//        f.add(b1);
//        f.add(t4);
//        f.add(l5);
//        f.add(l6);
//        f.add(cb1);
//        f.add(b2);
//
//
//        b0.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                p04();
//            }
//        }); //b0.addActionListener
//
//        b1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Random r = new Random();
//                StringBuilder rString = new StringBuilder();
//                for (int i = 0; i < 6; i++) {
//                    int digit = r.nextInt(16); // 0부터 15 사이의 난수 생성
//                    rString.append(Integer.toHexString(digit)); // 16진수로 변환하여 추가
//                }
//
//                l41.setText(rString.toString());
//            }
//        }); //b1.addActionListener
//
//        b2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (cb1.isSelected()) {//checkbox 처리확인후
//                    String pw = t3.getText();
//                    String answerR = t4.getText();
//                    String rString = l41.getText();
//
//                    boolean pwMatch = dao.loginUser(loginUser).getPw().equals(pw);
//                    boolean rResult = answerR.equals(rString);
//
//                    if (pwMatch && rResult) {
//
//                        int result = dao.deleteMember(loginUser);
//                        if (result == 1) {
//
//                            JOptionPane.showMessageDialog(f, "탈퇴 처리 되었습니다. 메인화면으로 돌아갑니다.");
//                            p01();
//                        } else {
//                            JOptionPane.showMessageDialog(f, "처리 실패. 다시 시도해주세요.");
//                        }
//                    } else if (pwMatch == false) {
//                        JOptionPane.showMessageDialog(f, "비밀번호 오류. 다시 입력해주세요");
//                    } else {
//                        JOptionPane.showMessageDialog(f, "자동입력방지 값이 일치하지 않습니다.");
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(f, "주의사항을 다시 확인해주세요.");
//                }
//            }
//        }); //b2.addActionListener
//        //JFrame Visible처리
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setVisible(true);
//    } // p04_2() : 회원탈퇴


    public void p06() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.CYAN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p06 : 상품조회");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JButton b1 = new JButton("필터 적용: p07()로 이동");
        // 검색버튼 구현
        //combobox
        String[] g1 = {"차종", "차량가격", "주문가능여부"};
        JComboBox combo1 = new JComboBox(g1);
        JTextField t1 = new JTextField(20); // 10은 글자수
        JButton b11 = new JButton("검색");

        //
        JButton b2 = new JButton("필터 등록: p06_1()로 이동");
        JButton b3 = new JButton("필터 수정/삭제: p06_2()로 이동");

        JButton b4 = new JButton("차량X 상세: p07_2()로 이동");

        f.add(b0);
        f.add(b1);
        f.add(combo1);
        f.add(t1);
        f.add(b11);

        f.add(b2);
        f.add(b3);
        f.add(b4);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p07();
            }
        }); //b1.addActionListener

        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = t1.getText();
                String criteria = combo1.getSelectedItem().toString();
                ProductDao productDao = new ProductDao();
                ArrayList<ProductDto> list = productDao.selectList(criteria, keyword);
                JOptionPane.showMessageDialog(f, list.isEmpty() ? "[요청하신 검색어에 대한 검색 결과가 존재하지 않습니다.]" : ("[요청하신 검색어에 대한 검색 결과입니다.]"+list));

            }
        }); //b11.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06_1();
            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06_2();
            }
        }); //b3.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p07_2();
            }
        }); //b3.addActionListener

        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p06() : 상품조회

    public void p06_1() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.CYAN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p06_1 : 필터 등록 페이지");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l2 = new JLabel("1. 승차감에 민감하신가요?");
        //radio
        ButtonGroup g1 = new ButtonGroup();
        JRadioButton r11 = new JRadioButton("맞습니다.");
        JRadioButton r12 = new JRadioButton("아닙니다.");

        JLabel l3 = new JLabel("2. 가진 짐이 많으신가요?");
        //radio
        ButtonGroup g2 = new ButtonGroup();
        JRadioButton r21 = new JRadioButton("맞습니다.");
        JRadioButton r22 = new JRadioButton("아닙니다.");

        JLabel l4 = new JLabel("3. 탑승인원이 3명을 넘어가나요?");
        //radio
        ButtonGroup g3 = new ButtonGroup();
        JRadioButton r31 = new JRadioButton("맞습니다.");
        JRadioButton r32 = new JRadioButton("아닙니다.");
        JButton b1 = new JButton("제출: p06()로 이동");

        f.add(b0);
        f.add(l2);
        g1.add(r11);
        g1.add(r12);
        f.add(r11);
        f.add(r12);

        f.add(l3);
        g2.add(r21);
        g2.add(r22);
        f.add(r21);
        f.add(r22);

        f.add(l4);
        g3.add(r31);
        g3.add(r32);
        f.add(r31);
        f.add(r32);
        f.add(b1);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f, "필터가 등록되었습니다.");

                PresetDao presetDao = new PresetDao(); // 쪽지 전달자!!
                PresetDto presetDto = new PresetDto(); // 쪽지
                //설문결과를 담아내는 작업 (빈쪽지를 채우는=set하는 작업)
                //presetDto.setPresetNum();         //안해도 된다! 어차피 오라클이 대신해주니까
                presetDto.setMemberNum(loginUser);
                presetDto.setPresetComfort(r11.isSelected() ? 1 : 0);
                presetDto.setPresetWeight(r21.isSelected() ? 1 : 0);
                presetDto.setPresetPassenger(r31.isSelected() ? 1 : 0);

                // DAO를 거친 후 result값 리턴받기
                int result = presetDao.insert(presetDto); //쪽지를 insert (DAO가 insert한다 DTO(쪽지)를 어디에? DB에), result에 성공여부만 알려드림.
                if (result == 1) JOptionPane.showMessageDialog(f, "필터가 등록되었습니다: " + presetDto);
                else JOptionPane.showMessageDialog(f, "필터가 등록되지 않았습니다.");

                p06();
            }
        }); //b1.addActionListener

        /////////////////////////////////////////////////////////
        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p06_1() : 필터등록페이지

    public void p06_2() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.CYAN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p06_2 : 필터 수정 & 삭제 페이지");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
/////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l2 = new JLabel("관리하실 필터를 선택해 주세요.");
        //radio
        ButtonGroup g1 = new ButtonGroup();
        JRadioButton r1 = new JRadioButton("1번 필터");
        JRadioButton r2 = new JRadioButton("2번 필터");
        JRadioButton r3 = new JRadioButton("3번 필터");

        JButton b1 = new JButton("수정: p06_1()로 이동");
        JButton b2 = new JButton("삭제: p06()로 이동");

        f.add(b0);
        f.add(l2);
        g1.add(r1);
        g1.add(r2);
        g1.add(r3);
        f.add(r1);
        f.add(r2);
        f.add(r3);

        f.add(b1);
        f.add(b2);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { p06(); }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 수정버튼 기능
                String presetNum = JOptionPane.showInputDialog("수정하실 필터를 선택해 주세요 예)PR1");

                PresetDto presetDto = new PresetDto();
                PresetDao presetDao = new PresetDao();

                //
                presetDto = presetDao.selectOne(presetNum);
                presetDto.setPresetComfort(Integer.parseInt(JOptionPane.showInputDialog(null, "승차감 편함/보통", presetDto.getPresetComfort())));
                presetDto.setPresetWeight(Integer.parseInt(JOptionPane.showInputDialog(null, "적재량 많음/적음", presetDto.getPresetWeight())));
                presetDto.setPresetPassenger(Integer.parseInt(JOptionPane.showInputDialog(null, "승객3인 이상 O/X", presetDto.getPresetPassenger())));


                int result = presetDao.update(presetDto);

                if(result == 1) {
                    JOptionPane.showMessageDialog(f, "필터가 수정되었습니다.");
                } else {
                    JOptionPane.showMessageDialog(f, "필터 수정에 실패하였습니다.");
                }



                // p06_1();
            }
        }); //b1.addActionListener



        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                //삭제버튼 기능
                String presetNum = JOptionPane.showInputDialog("삭제하실 필터를 선택해 주세요. 예)PR1");

                PresetDao presetDao = new PresetDao();

                int result = presetDao.delete(presetNum);
                if (result == 1) JOptionPane.showMessageDialog(f, "해당 필터가 삭제되었습니다.");
                else JOptionPane.showMessageDialog(f, "필터삭제에 실패했습니다.");

                p06();
            }
        }); //b2.addActionListener

        /////////////////////////////////////////////////////////
        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p06_2() : 필터수정삭제페이지

    public void p07() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.CYAN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p07 : 필터 적용");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JLabel l2 = new JLabel("사용할 필터를 선택해 주세요");
        //radio
        ButtonGroup g1 = new ButtonGroup();
        JRadioButton r1 = new JRadioButton("1번 필터");
        JRadioButton r2 = new JRadioButton("2번 필터");
        JRadioButton r3 = new JRadioButton("3번 필터");

        JLabel l3 = new JLabel("선택하신 필터의 조건입니다.");
        JLabel l4 = new JLabel("/ 승차감 l4.setText()");
        JLabel l5 = new JLabel("/ 적재량 l5.setText()");
        JLabel l6 = new JLabel("/ 탑승인원 l6.setText()");

        JButton b1 = new JButton("선택: p07_1()로 이동");

        f.add(b0);
        f.add(l2);
        g1.add(r1);
        g1.add(r2);
        g1.add(r3);
        f.add(r1);
        f.add(r2);
        f.add(r3);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(l6);

        f.add(b1);


        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String presetNum = JOptionPane.showInputDialog("적용하실 필터를 입력해 주세요");
                PresetDao presetDao = new PresetDao();
                PresetDto rsDto = presetDao.selectOne(presetNum);
                if (rsDto == null) JOptionPane.showMessageDialog(f, presetNum + " 필터 조건을 불러오는데 실패하였습니다");

                p07_1();
            }
        }); //b1.addActionListener

        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p07() : 필터적용

    public void p07_1() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.CYAN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p07_1 : 필터 활성화시 상품조회");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JButton b1 = new JButton("필터 적용해제: p06()로 이동");
        // 검색버튼 구현
        //combobox
        String[] g1 = {"차종", "차종분류", "취향"};
        JComboBox combo1 = new JComboBox(g1);
        JTextField t1 = new JTextField(20); // 10은 글자수
        JButton b11 = new JButton("검색");

        //
        JButton b2 = new JButton("필터 등록: p06_1()로 이동");
        JButton b3 = new JButton("필터 수정/삭제: p06_2()로 이동");
        JButton b4 = new JButton("GV80 상세: p07_2()로 이동");

        f.add(b0);
        f.add(b1);
        f.add(combo1);
        f.add(t1);
        f.add(b11);
        f.add(b2);
        f.add(b3);
        f.add(b4);


        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p07();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06();
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06_1();
            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06_2();
            }
        }); //b3.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p07_2();
            }
        }); //b4.addActionListener


        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p07_1() : 필터활성화 후 상품조회

    public void p07_2() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.CYAN);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p07_2 : 상품 정보 제공");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        //이미지
        JLabel img1 = new JLabel("이미지");
//        img1.setIcon(new ImageIcon("images/img.jpg"));
        //
        JLabel l2 = new JLabel("차종 l2.setText()");
        JLabel l3 = new JLabel("/ 차종분류 l3.setText()");
        JLabel l4 = new JLabel("/ 차종특징 l4.setText()");
        JLabel l5 = new JLabel("/ 차량상태 l5.setText()");
        JLabel l6 = new JLabel("/ 상품금액 l6.setText()");

        JButton b1 = new JButton("결제 후 이용: p08() 이동");

        f.add(b0);
        f.add(img1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(l6);

        f.add(b1);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p07_1();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p08();
            }
        }); //b1.addActionListener

        /////////////////////////////////////////////////////////
        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p07_2() : 상품정보제공
    public void p08() {
        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("결제페이지");
        f.getContentPane().setBackground(Color.GREEN);
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        JLabel l1 = new JLabel("p08 : 결제페이지-카드");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        JButton b0 = new JButton("<-뒤로가기");
        f.add(b0);
        JLabel l2 = new JLabel("카드 번호");
        JTextField t2 = new JTextField(19);
        JLabel l3 = new JLabel("예금자명");
        JTextField t3 = new JTextField(10);
        JLabel l4 = new JLabel("CVC번호: 뒷면 서명란 끝 3자리 숫자");
        JTextField t4 = new JTextField(3);
        JLabel l5 = new JLabel("유효기간: 월/년 각 2자리");
        JTextField t5 = new JTextField(4);

        ButtonGroup g1 = new ButtonGroup();
        JRadioButton r1 = new JRadioButton("카드 결제");
        String[] g2 = {"국민", "비씨", "신한"};
        JComboBox combo2 = new JComboBox(g2);
        String[] g3 = {"일시불", "2개월", "3개월", "4개월", "6개월", "12개월"};
        JComboBox combo3 = new JComboBox(g3);

        g1.add(r1);
        f.add(r1);
        f.add(combo2);
        f.add(combo3);
        f.add(l2);
        f.add(t2);
        f.add(l3);
        f.add(t3);
        f.add(l4);
        f.add(t4);
        f.add(l5);
        f.add(t5);

        JLabel l6 = new JLabel("주문 번호");
        JTextField t6 = new JTextField(10); // 주문 번호 입력 필드 추가
        f.add(l6);
        f.add(t6);

        JButton b1 = new JButton("결제하기: p09() 이동");
        f.add(b1);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p07_2();
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String orderNum = t6.getText(); // 주문 번호 입력값
                PaymentDto payment = new PaymentDto();
                payment.setPAYMENT_NUM("PMT" + System.currentTimeMillis()); // 고유한 결제 번호 생성
                payment.setORDER_NUM(orderNum);
                payment.setPAYMENT_AMOUNT(100.0); // 금액은 예시로 설정
                payment.setPAYMENT_METHOD(combo2.getSelectedItem().toString());

                PaymentDao paymentDao = new PaymentDao();
                boolean result = paymentDao.insertPayment(payment);

                if (result) {
                    JOptionPane.showMessageDialog(f, "결제 정보가 성공적으로 저장되었습니다.");
                    p09(payment.getPAYMENT_NUM());
                } else {
                    JOptionPane.showMessageDialog(f, "결제 정보 저장에 실패하였습니다.");
                }
            }
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void p09(String PAYMENT_NUM) {
        PaymentDao paymentDao = new PaymentDao();
        PaymentDto payment = paymentDao.getPayment(PAYMENT_NUM);

        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("결제 완료");

        JLabel l3 = new JLabel("주문번호: " + payment.getORDER_NUM());
        JLabel l9 = new JLabel("결제금액: " + payment.getPAYMENT_AMOUNT());
        JLabel l11 = new JLabel("결제수단: " + payment.getPAYMENT_METHOD());

        f.add(l3);
        f.add(l9);
        f.add(l11);

        JButton b1 = new JButton("확인: p03() 이동");
        f.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 첫 화면으로 이동하는 메서드 호출
                p03();
            }
        });

        JButton b2 = new JButton("결제취소: p10() 이동");
        f.add(b2);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 결제 취소화면
                p10(PAYMENT_NUM);
            }
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void p10(String PAYMENT_NUM) {
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("결제취소페이지");
        f.getContentPane().setBackground(Color.GREEN);
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        JLabel l1 = new JLabel("p10 : 결제페이지-결제취소");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        JButton b0 = new JButton("<-뒤로가기");
        JLabel l2 = new JLabel("결제 취소하시겠습니까?");
        JButton b1 = new JButton("확인: p11() 이동");

        f.add(b0);
        f.add(l2);
        f.add(b1);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p09(PAYMENT_NUM);
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p11(PAYMENT_NUM);
            }
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void p11(String PAYMENT_NUM) {
        PaymentDao paymentDao = new PaymentDao();
        boolean result = paymentDao.cancelPayment(PAYMENT_NUM);

        if (result) {
            JOptionPane.showMessageDialog(f, "결제가 성공적으로 취소되었습니다.");
            // 첫 화면으로 이동하는 메서드 호출
            p03();
        } else {
            JOptionPane.showMessageDialog(f, "결제 취소에 실패하였습니다.");
        }
    }

    public void p03B() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.MAGENTA);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p03B : 관리자메뉴");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        JButton b1 = new JButton("회원 관리: p03B_1()으로 이동");
        JButton b2 = new JButton("상품 관리: p03B_2()으로 이동");
        JButton b3 = new JButton("주문내역 관리: p03B_3()으로 이동");
        JButton b4 = new JButton("리뷰 관리: p03B_4()으로 이동");

        f.add(b0);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f, "관리자페이지 로그아웃");
                p02();
            }
        }); //b0.addActionListener

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_1();
            }
        }); //b1.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_2();
            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_3();
            }
        }); //b3.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_4();
            }
        }); //b4.addActionListener
        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p03B() : 관리자메뉴

    public void p03B_1() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.MAGENTA);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p03B_1 : 회원관리");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        //필터 및 검색
        JButton b1 = new JButton("필터 적용: 미구현");
        // 검색버튼 구현
        //combobox
        String[] g1 = {"회원번호", "아이디", "이름", "이메일", "관리자여부"};
        JComboBox combo1 = new JComboBox(g1);
        JTextField t1 = new JTextField(20); // 10은 글자수
        JButton b11 = new JButton("[검색]");
        //

        JButton b2 = new JButton("선택 해제: 미구현");
        JButton b3 = new JButton("[탈퇴]");

        JButton b4 = new JButton("회원M3(admin) [상세]");
        //
        JButton b91 = new JButton("회원 관리: p03B_1()으로 이동");
        JButton b92 = new JButton("상품 관리: p03B_2()으로 이동");
        JButton b93 = new JButton("주문내역 관리: p03B_3()으로 이동");
        JButton b94 = new JButton("리뷰 관리: p03B_4()으로 이동");

        b0.setBackground(Color.GREEN);
        b0.setOpaque(true);
        b1.setBackground(Color.RED);
        b1.setOpaque(true);
        b2.setBackground(Color.RED);
        b2.setOpaque(true);
        b11.setBackground(Color.YELLOW);
        b11.setOpaque(true);
        b3.setBackground(Color.YELLOW);
        b3.setOpaque(true);
        b4.setBackground(Color.YELLOW);
        b4.setOpaque(true);
        b91.setBackground(Color.GREEN);
        b91.setOpaque(true);
        b92.setBackground(Color.GREEN);
        b92.setOpaque(true);
        b93.setBackground(Color.GREEN);
        b93.setOpaque(true);
        b94.setBackground(Color.GREEN);
        b94.setOpaque(true);

        f.add(b0);
        f.add(b1);
        f.add(combo1);
        f.add(t1);
        f.add(b11);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(b91);
        f.add(b92);
        f.add(b93);
        f.add(b94);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B();
            }
        }); //b0.addActionListener

        //b1 b11 b2 b3 b4
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //미구현

            }
        }); //b1.addActionListener

        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //리스트 출력
                String keyword = t1.getText();
                int criteria = combo1.getSelectedIndex();
                MemberDao memberDao = new MemberDao();
                ArrayList<MemberDto> list = memberDao.selectList(criteria, keyword);
                JOptionPane.showMessageDialog(f, list.isEmpty() ? "[요청하신 검색어에 대한 검색 결과가 존재하지 않습니다.]" : ("[요청하신 검색어에 대한 검색 결과입니다.]"+list));
            }
        }); //b11.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p06_1();
            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //회원 탈퇴(D) 구현
                String memberNum = JOptionPane.showInputDialog("[1] 탈퇴처리할 회원번호를 입력해주세요. 예)M1");

                // DAO, DTO 선언 및 셋 (삭제는 DTO 필요 X)
                MemberDao memberDao = new MemberDao();

                // DAO를 거친 후 result값 리턴받기
                int result = memberDao.deleteMember(memberNum);
                if (result == 1) JOptionPane.showMessageDialog(f, "해당 회원이 탈퇴처리되었습니다.");
                else JOptionPane.showMessageDialog(f, "[ERROR] 해당 회원이 탈퇴처리되지 못했습니다.");
            }
        }); //b3.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //회원 상세(R) 구현
                String tmp = "admin";
                MemberDao memberDao = new MemberDao();
                MemberDto rsDto = memberDao.selectOne(tmp);
                //rsDto를 다시 DAO를 통해 DB로보냄.
                if(rsDto == null) JOptionPane.showMessageDialog(f, "[ERROR] 찾기에 실패하였습니다.");
                else JOptionPane.showMessageDialog(f, rsDto);
            }
        }); //b3.addActionListener

        b91.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_1();
            }
        }); //b1.addActionListener

        b92.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_2();
            }
        }); //b2.addActionListener

        b93.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_3();
            }
        }); //b3.addActionListener

        b94.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_4();
            }
        }); //b4.addActionListener
        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p03B_1() : 회원관리

    public void p03B_2() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.MAGENTA);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p03B_2 : 상품관리");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        //필터 및 검색
        JButton b1 = new JButton("필터 적용: 미구현");
        // 검색버튼 구현
        //combobox
        String[] g1 = {"차량고유번호", "차종번호", "차량상태", "상품가격", "주문가능여부"};
        JComboBox combo1 = new JComboBox(g1);
        JTextField t1 = new JTextField(20); // 10은 글자수
        JButton b11 = new JButton("[검색]");
        //

        JButton b2 = new JButton("선택해제: 미구현");
        JButton b3 = new JButton("[등록]");
        JButton b32 = new JButton("[삭제]");

        JButton b4 = new JButton("상품 P4의 상세 [수정]");
        //
        JButton b91 = new JButton("회원 관리: p03B_1()으로 이동");
        JButton b92 = new JButton("상품 관리: p03B_2()으로 이동");
        JButton b93 = new JButton("주문내역 관리: p03B_3()으로 이동");
        JButton b94 = new JButton("리뷰 관리: p03B_4()으로 이동");

        b0.setBackground(Color.GREEN);
        b0.setOpaque(true);
        b1.setBackground(Color.RED);
        b1.setOpaque(true);
        b2.setBackground(Color.RED);
        b2.setOpaque(true);
        b11.setBackground(Color.YELLOW);
        b11.setOpaque(true);
        b3.setBackground(Color.YELLOW);
        b3.setOpaque(true);
        b32.setBackground(Color.YELLOW);
        b32.setOpaque(true);
        b4.setBackground(Color.YELLOW);
        b4.setOpaque(true);
        b91.setBackground(Color.GREEN);
        b91.setOpaque(true);
        b92.setBackground(Color.GREEN);
        b92.setOpaque(true);
        b93.setBackground(Color.GREEN);
        b93.setOpaque(true);
        b94.setBackground(Color.GREEN);
        b94.setOpaque(true);

        f.add(b0);
        f.add(b1);
        f.add(combo1);
        f.add(t1);
        f.add(b11);
        f.add(b2);
        f.add(b3);
        f.add(b32);
        f.add(b4);
        f.add(b91);
        f.add(b92);
        f.add(b93);
        f.add(b94);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B();
            }
        }); //b0.addActionListener

        //b1 b11 b2 b3 b4
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }); //b1.addActionListener

        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //리스트 출력
                String keyword = t1.getText();
                int criteria = combo1.getSelectedIndex();
                ProductDao productDao = new ProductDao();
                ArrayList<ProductDto> list = productDao.selectList(criteria, keyword);
                JOptionPane.showMessageDialog(f, list.isEmpty() ? "[요청하신 검색어에 대한 검색 결과가 존재하지 않습니다.]" : ("[요청하신 검색어에 대한 검색 결과입니다.]"+list));

            }
        }); //b11.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //미구현
            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 상품 등록(C)
                String carNum = JOptionPane.showInputDialog("[1/4] 이 상품에 등록될 차종번호를 입력해주세요. 예)C1");
                String productStatus = JOptionPane.showInputDialog("[2/4] 이 상품(차량)의 현재 상태를 입력해주세요. 예)양호");
                String productPrice = JOptionPane.showInputDialog("[3/4] 이 상품의 (금액/일)의 값을 입력해주세요. 예)50000 쉼표를 붙이지 마세요.");
                String productAvailable = "1";

                // DAO, DTO 선언 및 셋
                ProductDao productDao = new ProductDao();
                ProductDto productDto = new ProductDto();
                productDto.setCarNum(carNum);
                productDto.setProductStatus(productStatus);
                productDto.setProductPrice(Integer.parseInt(productPrice));
                productDto.setProductAvailable(Integer.parseInt(productAvailable));

                // DAO를 거친 후 result값 리턴받기
                int result = productDao.insert(productDto);
                if (result == 1) JOptionPane.showMessageDialog(f, "상품이 등록되었습니다: " + productDto);
                else JOptionPane.showMessageDialog(f, "상품이 등록되지 않았습니다.");
            }
        }); //b3.addActionListener
        b32.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 상품 삭제(D) //선택한것을 받아 다시 구현해야함
                String productNum = JOptionPane.showInputDialog("[1] 삭제하기를 원하는 상품번호(차량고유번호)를 입력해주세요. 예)P1");

                // DAO, DTO 선언 및 셋 (삭제는 DTO 필요 X)
                ProductDao productDao = new ProductDao();

                // DAO를 거친 후 result값 리턴받기
                int result = productDao.delete(productNum);
                if (result == 1) JOptionPane.showMessageDialog(f, "해당 상품이 삭제되었습니다.");
                else JOptionPane.showMessageDialog(f, "해당 상품이 삭제되지 않았습니다.");
            }
        }); //b32.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 상품 상세 수정(U) //행 파라미터 함수로 다시 구현해야함
                String tmp = "P4";
                ProductDao productDao = new ProductDao();
                ProductDto rsDto = productDao.selectOne(tmp);
                //수정을 위해 다이얼로그를 띄움
                rsDto.setCarNum(JOptionPane.showInputDialog(null, "carNum: ", rsDto.getCarNum()));
                rsDto.setProductStatus(JOptionPane.showInputDialog(null, "productStatus: ", rsDto.getProductStatus()));
                rsDto.setProductPrice(Integer.parseInt(JOptionPane.showInputDialog(null, "productPrice: ", rsDto.getProductPrice())));
                rsDto.setProductAvailable(Integer.parseInt(JOptionPane.showInputDialog(null, "productAvailable: ", rsDto.getProductAvailable())));
                //rsDto를 다시 DAO를 통해 DB로보냄.
                int result = productDao.update(rsDto);
                if(result == 1) JOptionPane.showMessageDialog(f, "해당 상품에 대한 정보가 성공적으로 수정되었습니다."+rsDto);
                else JOptionPane.showMessageDialog(f, "[ERROR] 해당 상품에 대한 정보 수정에 실패하였습니다.");
            }
        }); //b4.addActionListener

        b91.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_1();
            }
        }); //b1.addActionListener

        b92.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_2();
            }
        }); //b2.addActionListener

        b93.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_3();
            }
        }); //b3.addActionListener

        b94.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_4();
            }
        }); //b4.addActionListener
        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p03B_2() : 상품관리

    public void p03B_3() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.MAGENTA);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p03B_3 : 주문내역관리");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);
        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        //필터 및 검색
        JButton b1 = new JButton("필터 적용: 미구현");
        // 검색버튼 구현
        //combobox
        String[] g1 = {"주문번호", "회원번호", "차량고유번호", "주문상태", "환불요청여부", "환불처리여부"};
        JComboBox combo1 = new JComboBox(g1);
        JTextField t1 = new JTextField(20); // 10은 글자수
        JButton b11 = new JButton("[검색]");
        //

        JButton b2 = new JButton("선택해제: 미구현");
        JButton b3 = new JButton("[환불처리]");

        JButton b4 = new JButton("주문O7 [상세]");
        //
        JButton b91 = new JButton("회원 관리: p03B_1()으로 이동");
        JButton b92 = new JButton("상품 관리: p03B_2()으로 이동");
        JButton b93 = new JButton("주문내역 관리: p03B_3()으로 이동");
        JButton b94 = new JButton("리뷰 관리: p03B_4()으로 이동");

        b0.setBackground(Color.GREEN);
        b0.setOpaque(true);
        b1.setBackground(Color.RED);
        b1.setOpaque(true);
        b2.setBackground(Color.RED);
        b2.setOpaque(true);
        b11.setBackground(Color.YELLOW);
        b11.setOpaque(true);
        b3.setBackground(Color.YELLOW);
        b3.setOpaque(true);
        b4.setBackground(Color.YELLOW);
        b4.setOpaque(true);
        b91.setBackground(Color.GREEN);
        b91.setOpaque(true);
        b92.setBackground(Color.GREEN);
        b92.setOpaque(true);
        b93.setBackground(Color.GREEN);
        b93.setOpaque(true);
        b94.setBackground(Color.GREEN);
        b94.setOpaque(true);

        f.add(b0);
        f.add(b1);
        f.add(combo1);
        f.add(t1);
        f.add(b11);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(b91);
        f.add(b92);
        f.add(b93);
        f.add(b94);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B();
            }
        }); //b0.addActionListener

        //b1 b11 b2 b3 b4
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }); //b1.addActionListener

        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //리스트 출력
                String keyword = t1.getText();
                int criteria = combo1.getSelectedIndex();
                OrderDao orderDao = new OrderDao();
                ArrayList<OrderDto> list = orderDao.selectList(criteria, keyword);
                JOptionPane.showMessageDialog(f, list.isEmpty() ? "[요청하신 검색어에 대한 검색 결과가 존재하지 않습니다.]" : ("[요청하신 검색어에 대한 검색 결과입니다.]"+list));
            }
        }); //b11.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //미구현
            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 주문 환불처리 (UorD)
                String orderNum = JOptionPane.showInputDialog("[1] 환불요청을 수락하여 삭제할 주문번호를 입력해주세요. 예)O1");
                // DAO, DTO 선언 및 셋 (삭제는 DTO 필요 X)
                OrderDao orderDao = new OrderDao();

                // DAO를 거친 후 result값 리턴받기
                int result = orderDao.delete(orderNum);
                if(result == 1) JOptionPane.showMessageDialog(f, "해당 주문이 환불처리 되어 목록에서 삭제되었습니다.");
                else JOptionPane.showMessageDialog(f, "[ERROR] 해당 주문이 목록에서 삭제되지 않았습니다.");
            }
        }); //b3.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //주문 상세(R) 구현
                String tmp = "O7";
                OrderDao orderDao = new OrderDao();
                OrderDto rsDto = orderDao.selectOne(tmp);
                //rsDto를 다시 DAO를 통해 DB로보냄.
                if(rsDto == null) JOptionPane.showMessageDialog(f, "[ERROR] 찾기에 실패하였습니다.");
                else JOptionPane.showMessageDialog(f, rsDto);
            }
        }); //b3.addActionListener

        b91.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_1();
            }
        }); //b1.addActionListener

        b92.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_2();
            }
        }); //b2.addActionListener

        b93.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_3();
            }
        }); //b3.addActionListener

        b94.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_4();
            }
        }); //b4.addActionListener
        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p03B_3() : 주문내역관리

    public void p03B_4() {
        //JFrame 정의
//        f = new JFrame();
        f.getContentPane().removeAll();
        f.repaint();
        f.setSize(400, 600);
        f.setTitle("첫화면");
        f.getContentPane().setBackground(Color.MAGENTA);

        // FlowLayout ?
        FlowLayout flow = new FlowLayout();
        f.setLayout(flow);

        //페이지제목
        JLabel l1 = new JLabel("p03B_4 : 리뷰관리");
        Font font = new Font("맑은 고딕", Font.BOLD, 30);
        l1.setFont(font);
        f.add(l1);

        /////////////////////////////////////////////////////////
        JButton b0 = new JButton("<-뒤로가기");
        //필터 및 검색
        JButton b1 = new JButton("필터 적용: 미구현");
        // 검색버튼 구현
        //combobox
        String[] g1 = {"리뷰번호", "주문번호", "차종평가별점", "후기제목", "후기내용"};
        JComboBox combo1 = new JComboBox(g1);
        JTextField t1 = new JTextField(20); // 10은 글자수
        JButton b11 = new JButton("[검색]");
        //

        JButton b2 = new JButton("선택해제: 미구현");
        JButton b3 = new JButton("[삭제]");

        JButton b4 = new JButton("리뷰R5 [상세]");
        //
        JButton b91 = new JButton("회원 관리: p03B_1()으로 이동");
        JButton b92 = new JButton("상품 관리: p03B_2()으로 이동");
        JButton b93 = new JButton("주문내역 관리: p03B_3()으로 이동");
        JButton b94 = new JButton("리뷰 관리: p03B_4()으로 이동");

        b0.setBackground(Color.GREEN);
        b0.setOpaque(true);
        b1.setBackground(Color.RED);
        b1.setOpaque(true);
        b2.setBackground(Color.RED);
        b2.setOpaque(true);
        b11.setBackground(Color.YELLOW);
        b11.setOpaque(true);
        b3.setBackground(Color.YELLOW);
        b3.setOpaque(true);
        b4.setBackground(Color.YELLOW);
        b4.setOpaque(true);
        b91.setBackground(Color.GREEN);
        b91.setOpaque(true);
        b92.setBackground(Color.GREEN);
        b92.setOpaque(true);
        b93.setBackground(Color.GREEN);
        b93.setOpaque(true);
        b94.setBackground(Color.GREEN);
        b94.setOpaque(true);

        f.add(b0);
        f.add(b1);
        f.add(combo1);
        f.add(t1);
        f.add(b11);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(b91);
        f.add(b92);
        f.add(b93);
        f.add(b94);

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B();
            }
        }); //b0.addActionListener

        //b1 b11 b2 b3 b4
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }); //b1.addActionListener

        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //리스트 출력
                String keyword = t1.getText();
                int criteria = combo1.getSelectedIndex();
                ReviewDao reviewDao = new ReviewDao();
                ArrayList<ReviewDto> list = reviewDao.selectList(criteria, keyword);
                JOptionPane.showMessageDialog(f, list.isEmpty() ? "[요청하신 검색어에 대한 검색 결과가 존재하지 않습니다.]" : ("[요청하신 검색어에 대한 검색 결과입니다.]"+list));
            }
        }); //b11.addActionListener

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //미구현
            }
        }); //b2.addActionListener

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 리뷰 삭제 구현(D)
                String reviewNum = JOptionPane.showInputDialog("[1] 삭제할 리뷰번호를 입력해주세요. 예)R1");
                // DAO, DTO 선언 및 셋 (삭제는 DTO 필요 X)
                ReviewDao reviewDao = new ReviewDao();

                // DAO를 거친 후 result값 리턴받기
                int result = reviewDao.delete(reviewNum);
                if(result == 1) JOptionPane.showMessageDialog(f, "해당 리뷰가 목록에서 삭제되었습니다.");
                else JOptionPane.showMessageDialog(f, "[ERROR] 해당 리뷰가 목록에서 삭제되지 않았습니다.");

            }
        }); //b3.addActionListener

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //리뷰 상세(R) 구현
                String tmp = "R5";
                ReviewDao reviewDao = new ReviewDao();
                ReviewDto rsDto = reviewDao.selectOne(tmp);
                //rsDto를 다시 DAO를 통해 DB로보냄.
                if(rsDto == null) JOptionPane.showMessageDialog(f, "[ERROR] 찾기에 실패하였습니다.");
                else JOptionPane.showMessageDialog(f, rsDto);
            }
        }); //b3.addActionListener

        b91.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_1();
            }
        }); //b1.addActionListener

        b92.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_2();
            }
        }); //b2.addActionListener

        b93.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_3();
            }
        }); //b3.addActionListener

        b94.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p03B_4();
            }
        }); //b4.addActionListener
        /////////////////////////////////////////////////////////

        //JFrame Visible처리
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    } // p03B_4() : 리뷰관리
}
