import java.util.*;
public class Msort{
	public static void mergeSort(int[] arr,int l,int r){
		if(l<r){
			int mid=(l+r)/2;
			mergeSort(arr,l,mid);
			mergeSort(arr,mid+1,r);
			merge(arr,l,mid,r);
		}
	}
	public static void merge(int[] arr,int l,int mid,int r){
		int n1=mid-l+1;
		int n2=r-mid;
		int[] lArr=new int[n1];
		int[] rArr=new int[n2];
		for(int i=0;i<n1;i++)
			lArr[i]=arr[l+i];
		for(int i=0;i<n2;i++)
			rArr[i]=arr[mid+1+i];
		int i=0;
		int j=0;
		int k=l;
		
		while(i<n1 && j<n2){
			if(lArr[i]<=rArr[j])
				arr[k++]=lArr[i++];
			else
				arr[k++]=rArr[j++];}
		while(i<n1)
			arr[k++]=lArr[i++];
		while(j<n2)
			arr[k++]=rArr[j++];
	
	}
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
       	System.out.print("Enter arr:");
       	for(int i=0;i<n;i++)
       		arr[i]=sc.nextInt();
        long start = System.nanoTime();
        mergeSort(arr, 0, n - 1);
        long end = System.nanoTime();
        System.out.println("\n\nSorted array:");
        for (int num : arr)
            System.out.print(num + " ");
        System.out.printf("\n\nExecution time: %.4f ms\n", (end - start) / 1e6);
    }
}
