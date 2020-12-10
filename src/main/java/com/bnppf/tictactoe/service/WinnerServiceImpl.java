package com.bnppf.tictactoe.service;

import org.springframework.stereotype.Service;

@Service
public class WinnerServiceImpl implements WinnerService {


    public boolean checkForWin() {

        return true;
    }

    private boolean checkRowsForWin()
    {
        return true;
    }
    private boolean checkColumnsForWin() {
        return true;
    }
    private boolean checkDiagonalsForWin() {
        return true;
    }
    private boolean checkRowCol(char choice1, char choice2, char choice3){

        return true;
    }
}
