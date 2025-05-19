package com.example.controller;

import com.example.domain.Item;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    //Sessionスコープを使うための設定
    @Autowired
    private HttpSession session;

    //Applicationスコープを使うための設定
    @Autowired
    private ServletContext application;



    @GetMapping("")
    public String index(Model model){

        //applicationスコープに商品一覧がなかったら商品一覧を作成し格納
        if(application.getAttribute("itemList") == null){
            LinkedList<Item> itemList = new LinkedList<>();

            Item item1 = new Item();
            item1.setName("手帳ノート");
            item1.setPrice(1000);
            itemList.add(item1);

            Item item2 = new Item();
            item2.setName("文房具セット");
            item2.setPrice(1500);
            itemList.add(item2);

            Item item3 = new Item();
            item3.setName("ファイル");
            item3.setPrice(2000);
            itemList.add(item3);

            application.setAttribute("itemList", itemList);
        }

        //sessionスコープにカートの内容が存在しなければ空のリストを作成し格納する
        if(session.getAttribute("shoppingCartList") == null){
            LinkedList<Item> shoppingCartList = new LinkedList<>();
            session.setAttribute("shoppingCartList", shoppingCartList);

            //合計金額計算
            model.addAttribute("totalPrice", calcTotalPrice(shoppingCartList));
        }

        return "item-and-cart";
    }


    @PostMapping("/inCart")
    public String inCart(String index, Model model){

        //セッションスコープからshoppingCartListを取得
        LinkedList<Item> shoppingCartList = (LinkedList<Item>) session.getAttribute("shoppingCartList");

        // applicationスコープから itemList を取得
        LinkedList<Item> itemList = (LinkedList<Item>) application.getAttribute("itemList");

        // 選択された商品をitemListから取得し、カートに追加
        Item selectedItem = itemList.get(Integer.parseInt(index));
        shoppingCartList.add(selectedItem);

        //合計金額計算
        model.addAttribute("totalPrice", calcTotalPrice(shoppingCartList));


        return index(model);
    }


    @PostMapping("/delete")
    public String delete(String index, Model model){

        //セッションスコープからshoppingCartListを取得
        LinkedList<Item> shoppingCartList = (LinkedList<Item>) session.getAttribute("shoppingCartList");

        // 選択されたindexの商品をshoppingCartListから削除
        int deleteIndex = Integer.parseInt(index);
        shoppingCartList.remove(deleteIndex);

        //合計金額計算
        model.addAttribute("totalPrice", calcTotalPrice(shoppingCartList));

        return index(model);
    }


    //合計金額計算
    public int calcTotalPrice(List<Item> itemList){
        int totalPrice = 0;
        for (Item cartItem : itemList){
            totalPrice += cartItem.getPrice();
        }
        return totalPrice;
    }
}