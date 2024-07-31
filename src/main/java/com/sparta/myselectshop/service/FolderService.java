package com.sparta.myselectshop.service;

import com.sparta.myselectshop.dto.FolderResponseDto;
import com.sparta.myselectshop.entity.Folder;
import com.sparta.myselectshop.entity.User;
import com.sparta.myselectshop.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;

    public void addFolders(List<String> folderNames, User user) {
        System.out.println("서비스 메소드 시작");
        List<Folder> existFolderList = null;

        try {

            existFolderList = folderRepository.findAllByUserAndNameIn(user, folderNames);
        }catch (NullPointerException e){
            existFolderList = new ArrayList<>();
        }

        List<Folder> folderList = new ArrayList<>();

        for (String folderName : folderNames) {
            if(!isExistFolderName(folderName, existFolderList)){
                Folder folder =  new Folder(folderName, user);
                folderList.add(folder);
            }
            else{
                throw new IllegalArgumentException("폴더명이 중복되었습니다.");
            }
        }
        folderRepository.saveAll(folderList);
    }
//    public List<FolderResponseDto> getFolders(User user) {
//        System.out.println((user.getUsername()));
//
//        List<Folder> folderList = folderRepository.findAllByUser(user);
//        System.out.println("계속이어짐");
//        if (folderList == null) {
//            return new ArrayList<>();
//        }
//        List<FolderResponseDto> responseDtoList = new ArrayList<>();
//        for (Folder folder : folderList) {
//            responseDtoList.add(new FolderResponseDto(folder));
//        }
//        return  responseDtoList;
//    }
public List<FolderResponseDto> getFolders(User user) {
    System.out.println((user.getUsername()));
    try {
        List<Folder> folderList = folderRepository.findAllByUser(user);


        System.out.println("계속이어짐");

        List<FolderResponseDto> responseDtoList = new ArrayList<>();
        for (Folder folder : folderList) {
            responseDtoList.add(new FolderResponseDto(folder));
        }
        return  responseDtoList;
    }catch (NullPointerException e){
        return new ArrayList<>();
    }
}



    private boolean isExistFolderName(String folderName, List<Folder> existFolderList) {
        for (Folder folder : existFolderList) {
            if(folderName.equals(folder.getName())){
                return true;
            }
        }
        return false;
    }


}
