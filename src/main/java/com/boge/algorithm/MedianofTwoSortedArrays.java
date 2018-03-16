package com.boge.algorithm;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 有两个大小为 m 和 n 的排序数组 nums1 和 nums2 。

       请找出两个排序数组的中位数并且总的运行时间复杂度为 O(log (m+n)) 。 
       
   nums1 = [1, 3]
   nums2 = [2]
       中位数是 2.0
 */
public class MedianofTwoSortedArrays {

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		if(nums1.length <= nums2.length){
			return getMid(nums1, nums2);
		}
		return getMid(nums2, nums1);
    }
	
	private static double getMid(int[] nums1, int[] nums2){
		
		if(nums1.length == 0){
			if(nums2.length != 0){
				if(nums2.length%2 == 0){
					return ((double)nums2[nums2.length/2]+(double)nums2[nums2.length/2-1])/2;
				}
				return nums2[nums2.length/2];
			}
		}
		
		int totalLength = nums1.length+nums2.length;
		int midIndex = (totalLength)/2;
		
		//先判断是否nums1都比nums2大或者小
		//判断nums1是否都比nums2大
		if(nums1[0]>=nums2[nums2.length-1]){
			//如果是奇数的情况，肯定在nums2中
			if(totalLength % 2 == 1){
				return nums2[midIndex];
			}else{
				//如果是偶数的情况，要么在nums2中，要么是nums[2]的最后一个元素或者nus[1]的第一个元素
				if(midIndex == nums2.length){
					return ((double)nums2[nums2.length-1]+(double)nums1[0])/2;
				}else{
					return ((double)nums2[midIndex-1]+(double)nums2[midIndex])/2;
				}
			}
		}
		//判断nums1是否都比nums2小
		else if(nums1[nums1.length-1]<=nums2[0]){
			//如果是奇数的情况，肯定在nums2中
			if(totalLength % 2 == 1){
				return nums2[midIndex-nums1.length];
			}else{
				//如果是偶数的情况，要么在nums2中，要么是nums[2]的第一个或者nums[1]的最后一个元素
				if(midIndex == nums2.length){
					return ((double)nums2[0]+(double)nums1[nums1.length-1])/2;
				}else{
					return ((double)nums2[midIndex-nums1.length-1]+(double)nums2[midIndex-nums1.length])/2;
				}
			}
		}else{
			//nums1和nums2穿插的情况
			int insertIndex = 0;
			int numsIndex = 0;
			double end = 0D;
			//如果是奇数的情况，肯定在nums2中
			if(totalLength % 2 == 1){
				for(int i=nums1.length-1;i>=0;i--){
					insertIndex = getIndex(nums1[i],nums2)+i;
					if(insertIndex == midIndex){
						return nums1[i];
					}else if(insertIndex < midIndex){
						//插入位置小于中位数的位置，说明中位数在nums2中
						return nums2[midIndex-i-1];
					}
				}
				return nums2[midIndex];
			}else{
				for(int i=nums1.length-1;i>=0;i--){
					numsIndex = getIndex(nums1[i],nums2)-1;
					insertIndex = numsIndex+i+1;
					if(insertIndex == midIndex){
						end = nums1[i];
						//获取前一个数
						if(i == 0){
							return (end+(double)nums2[numsIndex])/2;
						}else{
							//判断前一个数和nums1[i-1]谁大
							return (end+Math.max((double)nums2[numsIndex],(double)nums1[i-1]))/2;
						}
					}else if(insertIndex == midIndex-1){
						//插入的是midIndex-1的位置，那么前一个数一定是中位数之一
						return ((double)nums1[i]+(double)nums2[numsIndex+1])/2;
					}else if(insertIndex < midIndex){
						//插入位置小于中位数的位置，说明中位数在nums2中
						return ((double)nums2[midIndex-i-1]+(double)nums2[midIndex-i-2])/2;
					}
				}
				return ((double)nums2[midIndex]+(double)nums2[midIndex-1])/2;
			}
		}
		
	}
	
	public static int getIndex(double data,int[] nums){
		int start = 0;
		
		int end = nums.length-1;
		if(data < nums[start])return -1;
		else if(data > nums[end])return end+1;
		else{
			int mid = 0;
			while(start <= end){
				mid = (start+end)/2;
				if(data == nums[mid] || (data > nums[mid] && data < nums[mid+1])){
					break;
				}
				if(data > nums[mid]){
					start = start+1;
					continue;
				}
				if(data < nums[mid]){
					end = end-1;
					continue;
				}
			}
			return mid+1;
		}
		
	}
	
	public static String getFileMD5(File file) {
	    if (!file.isFile()) {
	        return null;
	    }
	    MessageDigest digest = null;
	    FileInputStream in = null;
	    byte buffer[] = new byte[8192];
	    int len;
	    try {
	        digest =MessageDigest.getInstance("MD5");
	        in = new FileInputStream(file);
	        while ((len = in.read(buffer)) != -1) {
	            digest.update(buffer, 0, len);
	        }
	        BigInteger bigInt = new BigInteger(1, digest.digest());
	        return bigInt.toString(16);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        try {
	            in.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	  
	}
	
	public static void main(String[] args) {
	}
	
}
