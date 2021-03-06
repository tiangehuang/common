/*
 * ����������� Matrix
 * 
 * �ܳ�������
 */
package com.yuan.common.math;

/**
 * ����������� Matrix

 * @author �ܳ���
 * @version 1.0
 */
public class Matrix 
{
	private int	numColumns = 0;			    // ��������
	private int	numRows = 0;				// ��������
	private double eps = 0.0;               // ȱʡ����
	private double[] elements = null;		// ������ݻ�����

	/**
	 * ���캯��
	 */
	public Matrix()
	{
		numColumns = 1;
		numRows = 1;
		init(numRows, numColumns);
	}

	/**
	 * ָ�����й��캯��
	 * 
	 * @param nRows - ָ���ľ�������
	 * @param nCols - ָ���ľ�������
	 */
	public Matrix(int nRows, int nCols)
	{
		numRows = nRows;
		numColumns = nCols;
		init(numRows, numColumns);
	}

	/**
	 * ָ��ֵ���캯��
	 * 
	 * @param nRows - ָ���ľ�������
	 * @param nCols - ָ���ľ�������
	 * @param value - һά���飬����ΪnRows*nCols���洢�����Ԫ�ص�ֵ
	 */
	public Matrix(int nRows, int nCols, double[] value)
	{
		numRows = nRows;
		numColumns = nCols;
		init(numRows, numColumns);
		setData(value);
	}

	/**
	 * �����캯��
	 * 
	 * @param nSize - ����������
	 */
	public Matrix(int nSize)
	{
		numRows = nSize;
		numColumns = nSize;
		init(nSize, nSize);
	}

	/**
	 * �����캯��
	 * 
	 * @param nSize - ����������
	 * @param value - һά���飬����ΪnRows*nRows���洢�����Ԫ�ص�ֵ
	 */
	public Matrix(int nSize, double[] value)
	{
		numRows = nSize;
		numColumns = nSize;
		init(nSize, nSize);
		setData(value);
	}

	/**
	 * �������캯��
	 * 
	 * @param other - Դ����
	 */
	public Matrix( Matrix other)
	{
		numColumns = other.getNumColumns();
		numRows = other.getNumRows();
		init(numRows, numColumns);
		setData(other.elements);
	}

	/**
	 * ��ʼ������
	 * 
	 * @param nRows - ָ���ľ�������
	 * @param nCols - ָ���ľ�������
	 * @return boolean, �ɹ�����true, ���򷵻�false
	 */
	public boolean init(int nRows, int nCols)
	{
		numRows = nRows;
		numColumns = nCols;
		int nSize = nCols*nRows;
		if (nSize < 0)
			return false;

		// �����ڴ�
		elements = new double[nSize];
		
		return true;
	}

	/**
	 * ���þ�������ľ���
	 * 
	 * @param newEps - �µľ���ֵ
	 */
	public void setEps(double newEps)
	{
		eps = newEps;
	}
	
	/**
	 * ȡ����ľ���ֵ
	 * 
	 * @return double�ͣ�����ľ���ֵ
	 */
	public double getEps()
	{
		return eps;
	}

	/**
	 * �������ʼ��Ϊ��λ����
	 * 
	 * @param nSize - ����������
	 * @return boolean �ͣ���ʼ���Ƿ�ɹ�
	 */
	public boolean makeUnitMatrix(int nSize)
	{
		if (! init(nSize, nSize))
			return false;

		for (int i=0; i<nSize; ++i)
			for (int j=0; j<nSize; ++j)
				if (i == j)
					setElement(i, j, 1);

		return true;
	}

	/**
	 * �������Ԫ�ص�ֵת��Ϊ�ַ�, Ԫ��֮��ķָ���Ϊ",", ������֮���лس����з�
	 * @return String �ͣ�ת���õ����ַ�
	 */
	public String toString() 
	{
		return toString(",", true);
	}
	
	/**
	 * �������Ԫ�ص�ֵת��Ϊ�ַ�
	 * 
	 * @param sDelim - Ԫ��֮��ķָ���
	 * @param bLineBreak - ������֮���Ƿ��лس����з�
	 * @return String �ͣ�ת���õ����ַ�
	 */
	public String toString(String sDelim, boolean bLineBreak) 
	{
		String s="";

		for (int i=0; i<numRows; ++i)
		{
			for (int j=0; j<numColumns; ++j)
			{
				String ss = new Float(getElement(i, j)).toString();
				s += ss;

				if (bLineBreak)
				{
					if (j != numColumns-1)
						s += sDelim;
				}
				else
				{
					if (i != numRows-1 || j != numColumns-1)
						s += sDelim;
				}
			}
			if (bLineBreak)
				if (i != numRows-1)
					s += "\r\n";
		}

		return s;
	}

	/**
	 * ������ָ�����и�Ԫ�ص�ֵת��Ϊ�ַ�
	 * 
	 * @param nRow - ָ���ľ����У�nRow = 0��ʾ��һ��
	 * @param sDelim - Ԫ��֮��ķָ���
	 * @return String �ͣ�ת���õ����ַ�
	 */
	public String toStringRow(int nRow,  String sDelim) 
	{
		String s = "";

		if (nRow >= numRows)
			return s;

		for (int j=0; j<numColumns; ++j)
		{
			String ss = new Float(getElement(nRow, j)).toString();
			s += ss;
			if (j != numColumns-1)
				s += sDelim;
		}

		return s;
	}

	/**
	 * ������ָ�����и�Ԫ�ص�ֵת��Ϊ�ַ�
	 * 
	 * @param nCol - ָ���ľ����У�nCol = 0��ʾ��һ��
	 * @param sDelim - Ԫ��֮��ķָ���
	 * @return String �ͣ�ת���õ����ַ�
	 */
	public String toStringCol(int nCol,  String sDelim /*= " "*/) 
	{
		String s = "";

		if (nCol >= numColumns)
			return s;

		for (int i=0; i<numRows; ++i)
		{
			String ss = new Float(getElement(i, nCol)).toString();
			s += ss;
			if (i != numRows-1)
				s += sDelim;
		}

		return s;
	}

	/**
	 * ���þ����Ԫ�ص�ֵ
	 * 
	 * @param value - һά���飬����ΪnumColumns*numRows���洢
     *	              �����Ԫ�ص�ֵ
	 */
	public void setData(double[] value)
	{
		elements = (double[])value.clone();
	}

	/**
	 * ����ָ��Ԫ�ص�ֵ
	 * 
	 * @param nRow - Ԫ�ص���
	 * @param nCol - Ԫ�ص���
	 * @param value - ָ��Ԫ�ص�ֵ
	 * @return boolean �ͣ�˵�������Ƿ�ɹ�
	 */
	public boolean setElement(int nRow, int nCol, double value)
	{
		if (nCol < 0 || nCol >= numColumns || nRow < 0 || nRow >= numRows)
			return false;						// array bounds error
		
		elements[nCol + nRow * numColumns] = value;

		return true;
	}

	/**
	 * ��ȡָ��Ԫ�ص�ֵ
	 * 
	 * @param nRow - Ԫ�ص���
	 * @param nCol - Ԫ�ص���
	 * @return double �ͣ�ָ��Ԫ�ص�ֵ
	 */
	public double getElement(int nRow, int nCol) 
	{
		return elements[nCol + nRow * numColumns] ;
	}

	/**
	 * ��ȡ���������
	 * 
	 * @return int �ͣ����������
	 */
	public int	getNumColumns() 
	{
		return numColumns;
	}

	/**
	 * ��ȡ���������
	 * @return int �ͣ����������
	 */
	public int	getNumRows() 
	{
		return numRows;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return double�����飬ָ������Ԫ�ص���ݻ�����
	 */
	public double[] getData() 
	{
		return elements;
	}

	/**
	 * ��ȡָ���е�����
	 * 
	 * @param nRow - �������ڵ���
	 * @param pVector - ָ�������и�Ԫ�صĻ�����
	 * @return int �ͣ�������Ԫ�صĸ�����������
	 */
	public int getRowVector(int nRow, double[] pVector) 
	{
		for (int j=0; j<numColumns; ++j)
			pVector[j] = getElement(nRow, j);

		return numColumns;
	}

	/**
	 * ��ȡָ���е�����
	 * 
	 * @param nCol - �������ڵ���
	 * @param pVector - ָ�������и�Ԫ�صĻ�����
	 * @return int �ͣ�������Ԫ�صĸ�����������
	 */
	public int getColVector(int nCol, double[] pVector) 
	{
		for (int i=0; i<numRows; ++i)
			pVector[i] = getElement(i, nCol);

		return numRows;
	}

	/**
	 * �����ֵ
	 * 
	 * @param other - ���ڸ����ֵ��Դ����
	 * @return Matrix�ͣ�����other���
	 */
	public Matrix setValue(Matrix other)
	{
		if (other != this)
		{
			init(other.getNumRows(), other.getNumColumns());
			setData(other.elements);
		}

		// finally return a reference to ourselves
		return this ;
	}

	/**
	 * �жϾ�������
	 * 
	 * @param other - ���ڱȽϵľ���
	 * @return boolean �ͣ��������������Ϊtrue������Ϊfalse
	 */
	public boolean equal(Matrix other) 
	{
		// ���ȼ���������Ƿ����
		if (numColumns != other.getNumColumns() || numRows != other.getNumRows())
			return false;

		for (int i=0; i<numRows; ++i)
		{
			for (int j=0; j<numColumns; ++j)
			{
				if (Math.abs(getElement(i, j) - other.getElement(i, j)) > eps)
					return false;
			}
		}

		return true;
	}

	/**
	 * ʵ�־���ļӷ�
	 * 
	 * @param other - ��ָ��������ӵľ���
	 * @return Matrix�ͣ�ָ��������other���֮��
	 */
	public Matrix add(Matrix other) 
	{
		// ���ȼ���������Ƿ����
		if (numColumns != other.getNumColumns() ||
			numRows != other.getNumRows())
			return null;

		// ���������
		Matrix	result = new Matrix(this) ;		// ��������
		
		// ����ӷ�
		for (int i = 0 ; i < numRows ; ++i)
		{
			for (int j = 0 ; j <  numColumns; ++j)
				result.setElement(i, j, result.getElement(i, j) + other.getElement(i, j)) ;
		}

		return result ;
	}

	/**
	 * ʵ�־���ļ���
	 * 
	 * @param other - ��ָ����������ľ���
	 * @return Matrix�ͣ�ָ��������other���֮��
	 */
	public Matrix	subtract(Matrix other) 
	{
		if (numColumns != other.getNumColumns() ||
				numRows != other.getNumRows())
				return null;

		// ���������
		Matrix	result = new Matrix(this) ;		// ��������

		// ���м�������
		for (int i = 0 ; i < numRows ; ++i)
		{
			for (int j = 0 ; j <  numColumns; ++j)
				result.setElement(i, j, result.getElement(i, j) - other.getElement(i, j)) ;
		}

		return result ;
	}

	/**
	 * ʵ�־�������
	 * 
	 * @param value - ��ָ��������˵�ʵ��
	 * @return Matrix�ͣ�ָ��������value���֮��
	 */
	public Matrix	multiply(double value) 
	{
		// ����Ŀ�����
		Matrix	result = new Matrix(this) ;		// copy ourselves
		
		// �������
		for (int i = 0 ; i < numRows ; ++i)
		{
			for (int j = 0 ; j <  numColumns; ++j)
				result.setElement(i, j, result.getElement(i, j) * value) ;
		}

		return result ;
	}

	/**
	 * ʵ�־���ĳ˷�
	 * 
	 * @param other - ��ָ��������˵ľ���
	 * @return Matrix�ͣ�ָ��������other���֮��
	 */
	public Matrix multiply(Matrix other) 
	{
		// ���ȼ���������Ƿ���Ҫ��
		if (numColumns != other.getNumRows())
			return null;

		// ruct the object we are going to return
		Matrix	result = new Matrix(numRows, other.getNumColumns());

		// ����˷�����
		//
		// [A][B][C]   [G][H]     [A*G + B*I + C*K][A*H + B*J + C*L]
		// [D][E][F] * [I][J] =   [D*G + E*I + F*K][D*H + E*J + F*L]
		//             [K][L]
		//
		double	value ;
		for (int i = 0 ; i < result.getNumRows() ; ++i)
		{
			for (int j = 0 ; j < other.getNumColumns() ; ++j)
			{
				value = 0.0 ;
				for (int k = 0 ; k < numColumns ; ++k)
				{
					value += getElement(i, k) * other.getElement(k, j) ;
				}

				result.setElement(i, j, value) ;
			}
		}

		return result ;
	}

	/**
	 * ������ĳ˷�
	 * 
	 * @param AR - ��߸������ʵ������
	 * @param AI - ��߸�������鲿����
	 * @param BR - �ұ߸������ʵ������
	 * @param BI - �ұ߸�������鲿����
	 * @param CR - �˻�����ʵ������
	 * @param CI - �˻������鲿����
	 * @return boolean�ͣ�������˷��Ƿ�ɹ�
	 */
	public boolean multiply(Matrix AR,  Matrix AI,  Matrix BR,  Matrix BI, Matrix CR, Matrix CI) 
	{
		// ���ȼ���������Ƿ���Ҫ��
		if (AR.getNumColumns() != AI.getNumColumns() ||
			AR.getNumRows() != AI.getNumRows() ||
			BR.getNumColumns() != BI.getNumColumns() ||
			BR.getNumRows() != BI.getNumRows() ||
			AR.getNumColumns() != BR.getNumRows())
			return false;

		// ����˻����ʵ��������鲿����
		Matrix mtxCR = new Matrix(AR.getNumRows(), BR.getNumColumns());
		Matrix mtxCI = new Matrix(AR.getNumRows(), BR.getNumColumns());
		// ���������
	    for (int i=0; i<AR.getNumRows(); ++i)
		{
		    for (int j=0; j<BR.getNumColumns(); ++j)
			{
				double vr = 0;
				double vi = 0;
	            for (int k =0; k<AR.getNumColumns(); ++k)
				{
	                double p = AR.getElement(i, k) * BR.getElement(k, j);
	                double q = AI.getElement(i, k) * BI.getElement(k, j);
	                double s = (AR.getElement(i, k) + AI.getElement(i, k)) * (BR.getElement(k, j) + BI.getElement(k, j));
	                vr += p - q;
	                vi += s - p - q;
				}
	            mtxCR.setElement(i, j, vr);
	            mtxCI.setElement(i, j, vi);
	        }
		}

		CR = mtxCR;
		CI = mtxCI;

		return true;
	}

	/**
	 * �����ת��
	 * 
	 * @return Matrix�ͣ�ָ������ת�þ���
	 */
	public Matrix transpose() 
	{
		// ����Ŀ�����
		Matrix	Trans = new Matrix(numColumns, numRows);

		// ת�ø�Ԫ��
		for (int i = 0 ; i < numRows ; ++i)
		{
			for (int j = 0 ; j < numColumns ; ++j)
				Trans.setElement(j, i, getElement(i, j)) ;
		}

		return Trans;
	}

	/**
	 * ʵ���������ȫѡ��Ԫ��˹��Լ����
	 * 
	 * @return boolean�ͣ������Ƿ�ɹ�
	 */
	public boolean invertGaussJordan()
	{
		int i,j,k,l,u,v;
	    double d = 0, p = 0;

		// �����ڴ�
	    int[] pnRow = new int[numColumns];
	    int[] pnCol = new int[numColumns];

		// ��Ԫ
	    for (k=0; k<=numColumns-1; k++)
	    { 
			d=0.0;
	        for (i=k; i<=numColumns-1; i++)
			{
				for (j=k; j<=numColumns-1; j++)
				{ 
					l=i*numColumns+j; p=Math.abs(elements[l]);
					if (p>d) 
					{ 
						d=p; 
						pnRow[k]=i; 
						pnCol[k]=j;
					}
				}
			}
	        
			// ʧ��
			if (d == 0.0)
			{
				return false;
			}

	        if (pnRow[k] != k)
			{
				for (j=0; j<=numColumns-1; j++)
				{ 
					u=k*numColumns+j; 
					v=pnRow[k]*numColumns+j;
					p=elements[u]; 
					elements[u]=elements[v]; 
					elements[v]=p;
				}
			}
	        
			if (pnCol[k] != k)
			{
				for (i=0; i<=numColumns-1; i++)
	            { 
					u=i*numColumns+k; 
					v=i*numColumns+pnCol[k];
					p=elements[u]; 
					elements[u]=elements[v]; 
					elements[v]=p;
	            }
			}

	        l=k*numColumns+k;
	        elements[l]=1.0/elements[l];
	        for (j=0; j<=numColumns-1; j++)
			{
				if (j != k)
	            { 
					u=k*numColumns+j; 
					elements[u]=elements[u]*elements[l];
				}
			}

	        for (i=0; i<=numColumns-1; i++)
			{
				if (i!=k)
				{
					for (j=0; j<=numColumns-1; j++)
					{
						if (j!=k)
						{ 
							u=i*numColumns+j;
							elements[u]=elements[u]-elements[i*numColumns+k]*elements[k*numColumns+j];
						}
	                }
				}
			}

	        for (i=0; i<=numColumns-1; i++)
			{
				if (i!=k)
	            { 
					u=i*numColumns+k; 
					elements[u]=-elements[u]*elements[l];
				}
			}
	    }

	    // ����ָ����д���
	    for (k=numColumns-1; k>=0; k--)
	    { 
			if (pnCol[k]!=k)
			{
				for (j=0; j<=numColumns-1; j++)
	            { 
					u=k*numColumns+j; 
					v=pnCol[k]*numColumns+j;
					p=elements[u]; 
					elements[u]=elements[v]; 
					elements[v]=p;
	            }
			}

	        if (pnRow[k]!=k)
			{
				for (i=0; i<=numColumns-1; i++)
	            { 
					u=i*numColumns+k; 
					v=i*numColumns+pnRow[k];
					p=elements[u]; 
					elements[u]=elements[v]; 
					elements[v]=p;
	            }
			}
	    }

		// �ɹ�����
		return true;
	}

	/**
	 * �����������ȫѡ��Ԫ��˹��Լ����
	 * 
	 * @param mtxImag - ��������鲿���󣬵�ǰ����Ϊ�������ʵ��
	 * @return boolean�ͣ������Ƿ�ɹ�
	 */
	public boolean invertGaussJordan(Matrix mtxImag)
	{
		int i,j,k,l,u,v,w;
	    double p,q,s,t,d,b;

		// �����ڴ�
	    int[] pnRow = new int[numColumns];
	    int[] pnCol = new int[numColumns];

		// ��Ԫ
	    for (k=0; k<=numColumns-1; k++)
	    { 
			d=0.0;
	        for (i=k; i<=numColumns-1; i++)
			{
				for (j=k; j<=numColumns-1; j++)
				{ 
					u=i*numColumns+j;
					p=elements[u]*elements[u]+mtxImag.elements[u]*mtxImag.elements[u];
					if (p>d) 
					{ 
						d=p; 
						pnRow[k]=i; 
						pnCol[k]=j;
					}
				}
			}

			// ʧ��
	        if (d == 0.0)
	        { 
	            return false;
	        }

	        if (pnRow[k]!=k)
			{
				for (j=0; j<=numColumns-1; j++)
	            { 
					u=k*numColumns+j; 
					v=pnRow[k]*numColumns+j;
					t=elements[u]; 
					elements[u]=elements[v]; 
					elements[v]=t;
					t=mtxImag.elements[u]; 
					mtxImag.elements[u]=mtxImag.elements[v]; 
					mtxImag.elements[v]=t;
	            }
			}

	        if (pnCol[k]!=k)
			{
				for (i=0; i<=numColumns-1; i++)
	            { 
					u=i*numColumns+k; 
					v=i*numColumns+pnCol[k];
					t=elements[u]; 
					elements[u]=elements[v]; 
					elements[v]=t;
					t=mtxImag.elements[u]; 
					mtxImag.elements[u]=mtxImag.elements[v]; 
					mtxImag.elements[v]=t;
	            }
			}

	        l=k*numColumns+k;
	        elements[l]=elements[l]/d; mtxImag.elements[l]=-mtxImag.elements[l]/d;
	        for (j=0; j<=numColumns-1; j++)
			{
				if (j!=k)
	            { 
					u=k*numColumns+j;
					p=elements[u]*elements[l]; 
					q=mtxImag.elements[u]*mtxImag.elements[l];
					s=(elements[u]+mtxImag.elements[u])*(elements[l]+mtxImag.elements[l]);
					elements[u]=p-q; 
					mtxImag.elements[u]=s-p-q;
	            }
			}

	        for (i=0; i<=numColumns-1; i++)
			{
				if (i!=k)
	            { 
					v=i*numColumns+k;
					for (j=0; j<=numColumns-1; j++)
					{
						if (j!=k)
						{ 
							u=k*numColumns+j;  
							w=i*numColumns+j;
							p=elements[u]*elements[v]; 
							q=mtxImag.elements[u]*mtxImag.elements[v];
							s=(elements[u]+mtxImag.elements[u])*(elements[v]+mtxImag.elements[v]);
							t=p-q; 
							b=s-p-q;
							elements[w]=elements[w]-t;
							mtxImag.elements[w]=mtxImag.elements[w]-b;
						}
					}
	            }
			}

	        for (i=0; i<=numColumns-1; i++)
			{
				if (i!=k)
	            { 
					u=i*numColumns+k;
					p=elements[u]*elements[l]; 
					q=mtxImag.elements[u]*mtxImag.elements[l];
					s=(elements[u]+mtxImag.elements[u])*(elements[l]+mtxImag.elements[l]);
					elements[u]=q-p; 
					mtxImag.elements[u]=p+q-s;
	            }
			}
	    }

	    // ����ָ����д���
	    for (k=numColumns-1; k>=0; k--)
	    { 
			if (pnCol[k]!=k)
			{
				for (j=0; j<=numColumns-1; j++)
	            { 
					u=k*numColumns+j; 
					v=pnCol[k]*numColumns+j;
					t=elements[u]; 
					elements[u]=elements[v]; 
					elements[v]=t;
					t=mtxImag.elements[u]; 
					mtxImag.elements[u]=mtxImag.elements[v]; 
					mtxImag.elements[v]=t;
	            }
			}

	        if (pnRow[k]!=k)
			{
				for (i=0; i<=numColumns-1; i++)
	            { 
					u=i*numColumns+k; 
					v=i*numColumns+pnRow[k];
					t=elements[u]; 
					elements[u]=elements[v]; 
					elements[v]=t;
					t=mtxImag.elements[u]; 
					mtxImag.elements[u]=mtxImag.elements[v]; 
					mtxImag.elements[v]=t;
	            }
			}
	    }

		// �ɹ�����
		return true;
	}

	/**
	 * �Գ�����������
	 * 
	 * @return boolean�ͣ������Ƿ�ɹ�
	 */
	public boolean invertSsgj()
	{ 
		int i, j ,k, m;
	    double w, g;

		// ��ʱ�ڴ�
	    double[] pTmp = new double[numColumns];

		// ���д���
	    for (k=0; k<=numColumns-1; k++)
	    { 
			w=elements[0];
	        if (w == 0.0)
	        { 
				return false;
			}

	        m=numColumns-k-1;
	        for (i=1; i<=numColumns-1; i++)
	        { 
				g=elements[i*numColumns]; 
				pTmp[i]=g/w;
	            if (i<=m) 
					pTmp[i]=-pTmp[i];
	            for (j=1; j<=i; j++)
	              elements[(i-1)*numColumns+j-1]=elements[i*numColumns+j]+g*pTmp[j];
	        }

	        elements[numColumns*numColumns-1]=1.0/w;
	        for (i=1; i<=numColumns-1; i++)
				elements[(numColumns-1)*numColumns+i-1]=pTmp[i];
	    }

		// ���е���
	    for (i=0; i<=numColumns-2; i++)
			for (j=i+1; j<=numColumns-1; j++)
				elements[i*numColumns+j]=elements[j*numColumns+i];

		return true;
	}

	/**
	 * �в����Ⱦ�������İ����ط���
	 * 
	 * @return boolean�ͣ������Ƿ�ɹ�
	 */
	public boolean invertTrench()
	{ 
		int i,j,k;
	    double a,s;

		// �����Ԫ��
		double[] t = new double[numColumns];
		// �����Ԫ��
		double[] tt = new double[numColumns];

		// �ϡ������Ԫ�ظ�ֵ
		for (i=0; i<numColumns; ++i)
		{
			t[i] = getElement(0, i);
		    tt[i] = getElement(i, 0);
		}

		// ��ʱ������
		double[] c = new double[numColumns];
		double[] r = new double[numColumns];
		double[] p = new double[numColumns];

		// ��Toeplitz���󣬷���
	    if (t[0] == 0.0)
	    { 
			return false;
	    }

	    a=t[0]; 
		c[0]=tt[1]/t[0]; 
		r[0]=t[1]/t[0];

	    for (k=0; k<=numColumns-3; k++)
	    { 
			s=0.0;
	        for (j=1; j<=k+1; j++)
				s=s+c[k+1-j]*tt[j];

	        s=(s-tt[k+2])/a;
			for (i=0; i<=k; i++)
				p[i]=c[i]+s*r[k-i];

	        c[k+1]=-s;
	        s=0.0;
	        for (j=1; j<=k+1; j++)
	          s=s+r[k+1-j]*t[j];
	        
			s=(s-t[k+2])/a;
	        for (i=0; i<=k; i++)
	        { 
				r[i]=r[i]+s*c[k-i];
	            c[k-i]=p[k-i];
	        }

	        r[k+1]=-s;
			a=0.0;
	        for (j=1; j<=k+2; j++)
	          a=a+t[j]*c[j-1];

	        a=t[0]-a;

			// ���ʧ��
	        if (a == 0.0)
			{ 
				return false;
			}
	    }

	    elements[0]=1.0/a;
	    for (i=0; i<=numColumns-2; i++)
	    { 
			k=i+1; 
			j=(i+1)*numColumns;
	        elements[k]=-r[i]/a; 
			elements[j]=-c[i]/a;
	    }

	   for (i=0; i<=numColumns-2; i++)
		{
			for (j=0; j<=numColumns-2; j++)
			{ 
				k=(i+1)*numColumns+j+1;
				elements[k]=elements[i*numColumns+j]-c[i]*elements[j+1];
				elements[k]=elements[k]+c[numColumns-j-2]*elements[numColumns-i-1];
			}
		}

		return true;
	}

	/**
	 * ������ʽֵ��ȫѡ��Ԫ��˹��ȥ��
	 * 
	 * @return double�ͣ�����ʽ��ֵ
	 */
	public double computeDetGauss()
	{ 
		int i,j,k,is = 0,js = 0,l,u,v;
	    double f,det,q,d;
	    
		// ��ֵ
		f=1.0; 
		det=1.0;
	    
		// ��Ԫ
		for (k=0; k<=numColumns-2; k++)
	    { 
			q=0.0;
	        for (i=k; i<=numColumns-1; i++)
			{
				for (j=k; j<=numColumns-1; j++)
				{ 
					l=i*numColumns+j; 
					d=Math.abs(elements[l]);
					if (d>q) 
					{ 
						q=d; 
						is=i; 
						js=j;
					}
				}
			}

	        if (q == 0.0)
	        { 
				det=0.0; 
				return(det);
			}
	        
			if (is!=k)
	        { 
				f=-f;
	            for (j=k; j<=numColumns-1; j++)
	            { 
					u=k*numColumns+j; 
					v=is*numColumns+j;
	                d=elements[u]; 
					elements[u]=elements[v]; 
					elements[v]=d;
	            }
	        }
	        
			if (js!=k)
	        { 
				f=-f;
	            for (i=k; i<=numColumns-1; i++)
	            {
					u=i*numColumns+js; 
					v=i*numColumns+k;
	                d=elements[u]; 
					elements[u]=elements[v]; 
					elements[v]=d;
	            }
	        }

	        l=k*numColumns+k;
	        det=det*elements[l];
	        for (i=k+1; i<=numColumns-1; i++)
	        { 
				d=elements[i*numColumns+k]/elements[l];
	            for (j=k+1; j<=numColumns-1; j++)
	            { 
					u=i*numColumns+j;
	                elements[u]=elements[u]-d*elements[k*numColumns+j];
	            }
	        }
	    }
	    
		// ��ֵ
		det=f*det*elements[numColumns*numColumns-1];

	    return(det);
	}

	/**
	 * ������ȵ�ȫѡ��Ԫ��˹��ȥ��
	 * 
	 * @return int�ͣ��������
	 */
	public int computeRankGauss()
	{ 
		int i,j,k,nn,is = 0,js = 0,l,ll,u,v;
	    double q,d;
	    
		// ��С�ڵ���������
		nn = numRows;
	    if (numRows >= numColumns) 
			nn = numColumns;

	    k=0;

		// ��Ԫ���
	    for (l=0; l<=nn-1; l++)
	    { 
			q=0.0;
	        for (i=l; i<=numRows-1; i++)
			{
				for (j=l; j<=numColumns-1; j++)
				{ 
					ll=i*numColumns+j; 
					d=Math.abs(elements[ll]);
					if (d>q) 
					{ 
						q=d; 
						is=i; 
						js=j;
					}
				}
			}

	        if (q == 0.0) 
				return(k);

	        k=k+1;
	        if (is!=l)
	        { 
				for (j=l; j<=numColumns-1; j++)
	            { 
					u=l*numColumns+j; 
					v=is*numColumns+j;
	                d=elements[u]; 
					elements[u]=elements[v]; 
					elements[v]=d;
	            }
	        }
	        if (js!=l)
	        { 
				for (i=l; i<=numRows-1; i++)
	            { 
					u=i*numColumns+js; 
					v=i*numColumns+l;
	                d=elements[u]; 
					elements[u]=elements[v]; 
					elements[v]=d;
	            }
	        }
	        
			ll=l*numColumns+l;
	        for (i=l+1; i<=numColumns-1; i++)
	        { 
				d=elements[i*numColumns+l]/elements[ll];
	            for (j=l+1; j<=numColumns-1; j++)
	            { 
					u=i*numColumns+j;
	                elements[u]=elements[u]-d*elements[l*numColumns+j];
	            }
	        }
	    }
	    
		return(k);
	}

	/**
	 * �Գ�����������˹��ֽ�������ʽ����ֵ
	 * 
	 * @param realDetValue - ��������ʽ��ֵ
	 * @return boolean�ͣ�����Ƿ�ɹ�
	 */
	public boolean computeDetCholesky(Real realDetValue)
	{ 
		int i,j,k,u,l;
	    double d, dblDet;
	    
		// ���������Ҫ��
		if (elements[0] <= 0.0)
			return false;

		// ����˹��ֽ�

	    elements[0]=Math.sqrt(elements[0]);
	    d=elements[0];

	    for (i=1; i<=numColumns-1; i++)
	    { 
			u=i*numColumns; 
			elements[u]=elements[u]/elements[0];
		}
	    
		for (j=1; j<=numColumns-1; j++)
	    { 
			l=j*numColumns+j;
	        for (k=0; k<=j-1; k++)
	        { 
				u=j*numColumns+k; 
				elements[l]=elements[l]-elements[u]*elements[u];
			}
	        
			if (elements[l] <= 0.0)
				return false;

	        elements[l]=Math.sqrt(elements[l]);
	        d=d*elements[l];
	        
			for (i=j+1; i<=numColumns-1; i++)
	        { 
				u=i*numColumns+j;
	            for (k=0; k<=j-1; k++)
					elements[u]=elements[u]-elements[i*numColumns+k]*elements[j*numColumns+k];
	            
				elements[u]=elements[u]/elements[l];
	        }
	    }
	    
		// ����ʽ��ֵ
		dblDet=d*d;
		realDetValue.setValue(dblDet);
		
		// ����Ǿ���
	    for (i=0; i<=numColumns-2; i++)
			for (j=i+1; j<=numColumns-1; j++)
				elements[i*numColumns+j]=0.0;

		return true;
	}

	/**
	 * �������Ƿֽ⣬�ֽ�ɹ���ԭ���󽫳�ΪQ����
	 * 
	 * @param mtxL - ���طֽ���L����
	 * @param mtxU - ���طֽ���U����
	 * @return boolean�ͣ�����Ƿ�ɹ�
	 */
	public boolean splitLU(Matrix mtxL, Matrix mtxU)
	{ 
		int i,j,k,w,v,ll;
	    
		// ��ʼ��������
		if (! mtxL.init(numColumns, numColumns) ||
			! mtxU.init(numColumns, numColumns))
			return false;

		for (k=0; k<=numColumns-2; k++)
	    { 
			ll=k*numColumns+k;
			if (elements[ll] == 0.0)
				return false;

	        for (i=k+1; i<=numColumns-1; i++)
			{ 
				w=i*numColumns+k; 
				elements[w]=elements[w]/elements[ll];
			}

	        for (i=k+1; i<=numColumns-1; i++)
	        { 
				w=i*numColumns+k;
	            for (j=k+1; j<=numColumns-1; j++)
	            { 
					v=i*numColumns+j;
	                elements[v]=elements[v]-elements[w]*elements[k*numColumns+j];
	            }
	        }
	    }
	    
		for (i=0; i<=numColumns-1; i++)
	    {
			for (j=0; j<i; j++)
	        { 
				w=i*numColumns+j; 
				mtxL.elements[w]=elements[w]; 
				mtxU.elements[w]=0.0;
			}

	        w=i*numColumns+i;
	        mtxL.elements[w]=1.0; 
			mtxU.elements[w]=elements[w];
	        
			for (j=i+1; j<=numColumns-1; j++)
	        { 
				w=i*numColumns+j; 
				mtxL.elements[w]=0.0; 
				mtxU.elements[w]=elements[w];
			}
	    }

		return true;
	}

	/**
	 * һ��ʵ�����QR�ֽ⣬�ֽ�ɹ���ԭ���󽫳�ΪR����
	 * 
	 * @param mtxQ - ���طֽ���Q����
	 * @return boolean�ͣ�����Ƿ�ɹ�
	 */
	public boolean splitQR(Matrix mtxQ)
	{ 
		int i,j,k,l,nn,p,jj;
	    double u,alpha,w,t;
	    
		if (numRows < numColumns)
			return false;

		// ��ʼ��Q����
		if (! mtxQ.init(numRows, numRows))
			return false;

		// �Խ���Ԫ�ص�λ��
	    for (i=0; i<=numRows-1; i++)
		{
			for (j=0; j<=numRows-1; j++)
			{ 
				l=i*numRows+j; 
				mtxQ.elements[l]=0.0;
				if (i==j) 
					mtxQ.elements[l]=1.0;
			}
		}

		// ��ʼ�ֽ�

	    nn=numColumns;
	    if (numRows == numColumns) 
			nn=numRows-1;

	    for (k=0; k<=nn-1; k++)
	    { 
			u=0.0; 
			l=k*numColumns+k;
	        for (i=k; i<=numRows-1; i++)
	        { 
				w=Math.abs(elements[i*numColumns+k]);
	            if (w>u) 
					u=w;
	        }
	        
			alpha=0.0;
	        for (i=k; i<=numRows-1; i++)
	        { 
				t=elements[i*numColumns+k]/u; 
				alpha=alpha+t*t;
			}

	        if (elements[l]>0.0) 
				u=-u;

	        alpha=u*Math.sqrt(alpha);
	        if (alpha == 0.0)
				return false;

	        u=Math.sqrt(2.0*alpha*(alpha-elements[l]));
	        if ((u+1.0)!=1.0)
	        { 
				elements[l]=(elements[l]-alpha)/u;
	            for (i=k+1; i<=numRows-1; i++)
	            { 
					p=i*numColumns+k; 
					elements[p]=elements[p]/u;
				}
	            
				for (j=0; j<=numRows-1; j++)
	            { 
					t=0.0;
	                for (jj=k; jj<=numRows-1; jj++)
						t=t+elements[jj*numColumns+k]*mtxQ.elements[jj*numRows+j];

	                for (i=k; i<=numRows-1; i++)
	                { 
						p=i*numRows+j; 
						mtxQ.elements[p]=mtxQ.elements[p]-2.0*t*elements[i*numColumns+k];
					}
	            }
	            
				for (j=k+1; j<=numColumns-1; j++)
	            { 
					t=0.0;
	                
					for (jj=k; jj<=numRows-1; jj++)
						t=t+elements[jj*numColumns+k]*elements[jj*numColumns+j];
	                
					for (i=k; i<=numRows-1; i++)
	                { 
						p=i*numColumns+j; 
						elements[p]=elements[p]-2.0*t*elements[i*numColumns+k];
					}
	            }
	            
				elements[l]=alpha;
	            for (i=k+1; i<=numRows-1; i++)
					elements[i*numColumns+k]=0.0;
	        }
	    }
	    
		// ����Ԫ��
		for (i=0; i<=numRows-2; i++)
		{
			for (j=i+1; j<=numRows-1;j++)
			{ 
				p=i*numRows+j; 
				l=j*numRows+i;
				t=mtxQ.elements[p]; 
				mtxQ.elements[p]=mtxQ.elements[l]; 
				mtxQ.elements[l]=t;
			}
		}

		return true;
	}

	/**
	 * һ��ʵ���������ֵ�ֽ⣬�ֽ�ɹ���ԭ����Խ���Ԫ�ؾ��Ǿ��������ֵ
	 * 
	 * @param mtxU - ���طֽ���U����
	 * @param mtxV - ���طֽ���V����
	 * @param eps - ���㾫��
	 * @return boolean�ͣ�����Ƿ�ɹ�
	 */
	public boolean splitUV(Matrix mtxU, Matrix mtxV, double eps)
	{ 
		int i,j,k,l,it,ll,kk,ix,iy,mm,nn,iz,m1,ks;
	    double d,dd,t,sm,sm1,em1,sk,ek,b,c,shh;
	    double[] fg = new double[2];
	    double[] cs = new double[2];

		int m = numRows;
		int n = numColumns;

		// ��ʼ��U, V����
		if (! mtxU.init(m, m) || ! mtxV.init(n, n))
			return false;

		// ��ʱ������
		int ka = Math.max(m, n) + 1;
		double[] s = new double[ka];
		double[] e = new double[ka];
		double[] w = new double[ka];

		// ָ��������Ϊ60
	    it=60; 
		k=n;

	    if (m-1<n) 
			k=m-1;

	    l=m;
	    if (n-2<m) 
			l=n-2;
	    if (l<0) 
			l=0;

		// ѭ��������
	    ll=k;
	    if (l>k) 
			ll=l;
	    if (ll>=1)
	    { 
			for (kk=1; kk<=ll; kk++)
	        { 
				if (kk<=k)
	            { 
					d=0.0;
	                for (i=kk; i<=m; i++)
	                { 
						ix=(i-1)*n+kk-1; 
						d=d+elements[ix]*elements[ix];
					}

	                s[kk-1]=Math.sqrt(d);
	                if (s[kk-1]!=0.0)
	                { 
						ix=(kk-1)*n+kk-1;
	                    if (elements[ix]!=0.0)
	                    { 
							s[kk-1]=Math.abs(s[kk-1]);
	                        if (elements[ix]<0.0) 
								s[kk-1]=-s[kk-1];
	                    }
	                    
						for (i=kk; i<=m; i++)
	                    { 
							iy=(i-1)*n+kk-1;
	                        elements[iy]=elements[iy]/s[kk-1];
	                    }
	                    
						elements[ix]=1.0+elements[ix];
	                }
	                
					s[kk-1]=-s[kk-1];
	            }
	            
				if (n>=kk+1)
	            { 
					for (j=kk+1; j<=n; j++)
	                { 
						if ((kk<=k)&&(s[kk-1]!=0.0))
	                    { 
							d=0.0;
	                        for (i=kk; i<=m; i++)
	                        { 
								ix=(i-1)*n+kk-1;
	                            iy=(i-1)*n+j-1;
	                            d=d+elements[ix]*elements[iy];
	                        }
	                        
							d=-d/elements[(kk-1)*n+kk-1];
	                        for (i=kk; i<=m; i++)
	                        { 
								ix=(i-1)*n+j-1;
	                            iy=(i-1)*n+kk-1;
	                            elements[ix]=elements[ix]+d*elements[iy];
	                        }
	                    }
	                    
						e[j-1]=elements[(kk-1)*n+j-1];
	                }
	            }
	            
				if (kk<=k)
	            { 
					for (i=kk; i<=m; i++)
	                { 
						ix=(i-1)*m+kk-1; 
						iy=(i-1)*n+kk-1;
	                    mtxU.elements[ix]=elements[iy];
	                }
	            }
	            
				if (kk<=l)
	            { 
					d=0.0;
	                for (i=kk+1; i<=n; i++)
						d=d+e[i-1]*e[i-1];
	                
					e[kk-1]=Math.sqrt(d);
	                if (e[kk-1]!=0.0)
	                { 
						if (e[kk]!=0.0)
	                    { 
							e[kk-1]=Math.abs(e[kk-1]);
	                        if (e[kk]<0.0) 
								e[kk-1]=-e[kk-1];
	                    }

	                    for (i=kk+1; i<=n; i++)
	                      e[i-1]=e[i-1]/e[kk-1];
	                    
						e[kk]=1.0+e[kk];
	                }
	                
					e[kk-1]=-e[kk-1];
	                if ((kk+1<=m)&& (e[kk-1]!=0.0))
	                { 
						for (i=kk+1; i<=m; i++) 
							w[i-1]=0.0;
	                    
						for (j=kk+1; j<=n; j++)
							for (i=kk+1; i<=m; i++)
								w[i-1]=w[i-1]+e[j-1]*elements[(i-1)*n+j-1];
	                    
						for (j=kk+1; j<=n; j++)
						{
							for (i=kk+1; i<=m; i++)
	                        { 
								ix=(i-1)*n+j-1;
								elements[ix]=elements[ix]-w[i-1]*e[j-1]/e[kk];
	                        }
						}
	                }
	                
					for (i=kk+1; i<=n; i++)
	                  mtxV.elements[(i-1)*n+kk-1]=e[i-1];
	            }
	        }
	    }
	    
		mm=n;
	    if (m+1<n) 
			mm=m+1;
	    if (k<n) 
			s[k]=elements[k*n+k];
	    if (m<mm) 
			s[mm-1]=0.0;
	    if (l+1<mm) 
			e[l]=elements[l*n+mm-1];

	    e[mm-1]=0.0;
	    nn=m;
	    if (m>n) 
			nn=n;
	    if (nn>=k+1)
	    { 
			for (j=k+1; j<=nn; j++)
	        { 
				for (i=1; i<=m; i++)
					mtxU.elements[(i-1)*m+j-1]=0.0;
	            mtxU.elements[(j-1)*m+j-1]=1.0;
	        }
	    }
	    
		if (k>=1)
	    { 
			for (ll=1; ll<=k; ll++)
	        { 
				kk=k-ll+1; 
				iz=(kk-1)*m+kk-1;
	            if (s[kk-1]!=0.0)
	            { 
					if (nn>=kk+1)
					{
						for (j=kk+1; j<=nn; j++)
						{ 
							d=0.0;
							for (i=kk; i<=m; i++)
							{ 
								ix=(i-1)*m+kk-1;
								iy=(i-1)*m+j-1;
								d=d+mtxU.elements[ix]*mtxU.elements[iy]/mtxU.elements[iz];
							}

							d=-d;
							for (i=kk; i<=m; i++)
							{ 
								ix=(i-1)*m+j-1;
								iy=(i-1)*m+kk-1;
								mtxU.elements[ix]=mtxU.elements[ix]+d*mtxU.elements[iy];
							}
						}
					}
	                  
					for (i=kk; i<=m; i++)
					{ 
						ix=(i-1)*m+kk-1; 
						mtxU.elements[ix]=-mtxU.elements[ix];
					}

					mtxU.elements[iz]=1.0+mtxU.elements[iz];
					if (kk-1>=1)
					{
						for (i=1; i<=kk-1; i++)
							mtxU.elements[(i-1)*m+kk-1]=0.0;
					}
				}
	            else
	            { 
					for (i=1; i<=m; i++)
						mtxU.elements[(i-1)*m+kk-1]=0.0;
	                mtxU.elements[(kk-1)*m+kk-1]=1.0;
	            }
			}
	    }

	    for (ll=1; ll<=n; ll++)
	    { 
			kk=n-ll+1; 
			iz=kk*n+kk-1;
	        
			if ((kk<=l) && (e[kk-1]!=0.0))
	        { 
				for (j=kk+1; j<=n; j++)
	            { 
					d=0.0;
	                for (i=kk+1; i<=n; i++)
	                { 
						ix=(i-1)*n+kk-1; 
						iy=(i-1)*n+j-1;
	                    d=d+mtxV.elements[ix]*mtxV.elements[iy]/mtxV.elements[iz];
	                }
	                
					d=-d;
	                for (i=kk+1; i<=n; i++)
	                { 
						ix=(i-1)*n+j-1; 
						iy=(i-1)*n+kk-1;
	                    mtxV.elements[ix]=mtxV.elements[ix]+d*mtxV.elements[iy];
	                }
	            }
	        }
	        
			for (i=1; i<=n; i++)
				mtxV.elements[(i-1)*n+kk-1]=0.0;
	        
			mtxV.elements[iz-n]=1.0;
	    }
	    
		for (i=1; i<=m; i++)
			for (j=1; j<=n; j++)
				elements[(i-1)*n+j-1]=0.0;
	    
		m1=mm; 
		it=60;
	    while (true)
	    { 
			if (mm==0)
	        { 
				ppp(elements,e,s,mtxV.elements,m,n);
	            return true;
	        }
	        if (it==0)
	        { 
				ppp(elements,e,s,mtxV.elements,m,n);
	            return false;
	        }
	        
			kk=mm-1;
			while ((kk!=0) && (Math.abs(e[kk-1])!=0.0))
	        { 
				d=Math.abs(s[kk-1])+Math.abs(s[kk]);
	            dd=Math.abs(e[kk-1]);
	            if (dd>eps*d) 
					kk=kk-1;
	            else 
					e[kk-1]=0.0;
	        }
	        
			if (kk==mm-1)
	        { 
				kk=kk+1;
	            if (s[kk-1]<0.0)
	            { 
					s[kk-1]=-s[kk-1];
	                for (i=1; i<=n; i++)
	                { 
						ix=(i-1)*n+kk-1; 
						mtxV.elements[ix]=-mtxV.elements[ix];}
					}
					
					while ((kk!=m1) && (s[kk-1]<s[kk]))
					{ 
						d=s[kk-1]; 
						s[kk-1]=s[kk]; 
						s[kk]=d;
						if (kk<n)
						{
							for (i=1; i<=n; i++)
							{ 
								ix=(i-1)*n+kk-1; 
								iy=(i-1)*n+kk;
								d=mtxV.elements[ix]; 
								mtxV.elements[ix]=mtxV.elements[iy]; 
								mtxV.elements[iy]=d;
							}
						}

						if (kk<m)
						{
							for (i=1; i<=m; i++)
							{ 
								ix=(i-1)*m+kk-1; 
								iy=(i-1)*m+kk;
								d=mtxU.elements[ix]; 
								mtxU.elements[ix]=mtxU.elements[iy]; 
								mtxU.elements[iy]=d;
							}
						}

						kk=kk+1;
	            }
	            
				it=60;
	            mm=mm-1;
	        }
	        else
	        { 
				ks=mm;
	            while ((ks>kk) && (Math.abs(s[ks-1])!=0.0))
	            { 
					d=0.0;
	                if (ks!=mm) 
						d=d+Math.abs(e[ks-1]);
	                if (ks!=kk+1) 
						d=d+Math.abs(e[ks-2]);
	                
					dd=Math.abs(s[ks-1]);
	                if (dd>eps*d) 
						ks=ks-1;
	                else 
						s[ks-1]=0.0;
	            }
	            
				if (ks==kk)
	            { 
					kk=kk+1;
	                d=Math.abs(s[mm-1]);
	                t=Math.abs(s[mm-2]);
	                if (t>d) 
						d=t;
	                
					t=Math.abs(e[mm-2]);
	                if (t>d) 
						d=t;
	                
					t=Math.abs(s[kk-1]);
	                if (t>d) 
						d=t;
	                
					t=Math.abs(e[kk-1]);
	                if (t>d) 
						d=t;
	                
					sm=s[mm-1]/d; 
					sm1=s[mm-2]/d;
	                em1=e[mm-2]/d;
	                sk=s[kk-1]/d; 
					ek=e[kk-1]/d;
	                b=((sm1+sm)*(sm1-sm)+em1*em1)/2.0;
	                c=sm*em1; 
					c=c*c; 
					shh=0.0;

	                if ((b!=0.0)||(c!=0.0))
	                { 
						shh=Math.sqrt(b*b+c);
	                    if (b<0.0) 
							shh=-shh;

	                    shh=c/(b+shh);
	                }
	                
					fg[0]=(sk+sm)*(sk-sm)-shh;
	                fg[1]=sk*ek;
	                for (i=kk; i<=mm-1; i++)
	                { 
						sss(fg,cs);
	                    if (i!=kk) 
							e[i-2]=fg[0];

	                    fg[0]=cs[0]*s[i-1]+cs[1]*e[i-1];
	                    e[i-1]=cs[0]*e[i-1]-cs[1]*s[i-1];
	                    fg[1]=cs[1]*s[i];
	                    s[i]=cs[0]*s[i];

	                    if ((cs[0]!=1.0)||(cs[1]!=0.0))
						{
							for (j=1; j<=n; j++)
	                        { 
								ix=(j-1)*n+i-1;
								iy=(j-1)*n+i;
								d=cs[0]*mtxV.elements[ix]+cs[1]*mtxV.elements[iy];
								mtxV.elements[iy]=-cs[1]*mtxV.elements[ix]+cs[0]*mtxV.elements[iy];
								mtxV.elements[ix]=d;
	                        }
						}

	                    sss(fg,cs);
	                    s[i-1]=fg[0];
	                    fg[0]=cs[0]*e[i-1]+cs[1]*s[i];
	                    s[i]=-cs[1]*e[i-1]+cs[0]*s[i];
	                    fg[1]=cs[1]*e[i];
	                    e[i]=cs[0]*e[i];

	                    if (i<m)
						{
							if ((cs[0]!=1.0)||(cs[1]!=0.0))
							{
								for (j=1; j<=m; j++)
								{ 
									ix=(j-1)*m+i-1;
									iy=(j-1)*m+i;
									d=cs[0]*mtxU.elements[ix]+cs[1]*mtxU.elements[iy];
									mtxU.elements[iy]=-cs[1]*mtxU.elements[ix]+cs[0]*mtxU.elements[iy];
									mtxU.elements[ix]=d;
								}
							}
						}
	                }
	                
					e[mm-2]=fg[0];
	                it=it-1;
	            }
	            else
	            { 
					if (ks==mm)
	                { 
						kk=kk+1;
	                    fg[1]=e[mm-2]; 
						e[mm-2]=0.0;
	                    for (ll=kk; ll<=mm-1; ll++)
	                    { 
							i=mm+kk-ll-1;
	                        fg[0]=s[i-1];
	                        sss(fg,cs);
	                        s[i-1]=fg[0];
	                        if (i!=kk)
	                        { 
								fg[1]=-cs[1]*e[i-2];
	                            e[i-2]=cs[0]*e[i-2];
	                        }
	                        
							if ((cs[0]!=1.0)||(cs[1]!=0.0))
							{
								for (j=1; j<=n; j++)
	                            { 
									ix=(j-1)*n+i-1;
									iy=(j-1)*n+mm-1;
									d=cs[0]*mtxV.elements[ix]+cs[1]*mtxV.elements[iy];
									mtxV.elements[iy]=-cs[1]*mtxV.elements[ix]+cs[0]*mtxV.elements[iy];
									mtxV.elements[ix]=d;
	                            }
							}
	                    }
	                }
	                else
	                { 
						kk=ks+1;
	                    fg[1]=e[kk-2];
	                    e[kk-2]=0.0;
	                    for (i=kk; i<=mm; i++)
	                    { 
							fg[0]=s[i-1];
	                        sss(fg,cs);
	                        s[i-1]=fg[0];
	                        fg[1]=-cs[1]*e[i-1];
	                        e[i-1]=cs[0]*e[i-1];
	                        if ((cs[0]!=1.0)||(cs[1]!=0.0))
							{
								for (j=1; j<=m; j++)
	                            { 
									ix=(j-1)*m+i-1;
									iy=(j-1)*m+kk-2;
									d=cs[0]*mtxU.elements[ix]+cs[1]*mtxU.elements[iy];
									mtxU.elements[iy]=-cs[1]*mtxU.elements[ix]+cs[0]*mtxU.elements[iy];
									mtxU.elements[ix]=d;
	                            }
							}
	                    }
	                }
	            }
	        }
	    }
	}

	/**
	 * �ڲ�������SplitUV�������
	 */
	private void ppp(double[] a, double[] e, double[] s, double[] v, int m, int n)
	{ 
		int i,j,p,q;
	    double d;

	    if (m>=n) 
			i=n;
	    else 
			i=m;

	    for (j=1; j<=i-1; j++)
	    { 
			a[(j-1)*n+j-1]=s[j-1];
	        a[(j-1)*n+j]=e[j-1];
	    }
	    
		a[(i-1)*n+i-1]=s[i-1];
	    if (m<n) 
			a[(i-1)*n+i]=e[i-1];
	    
		for (i=1; i<=n-1; i++)
		{
			for (j=i+1; j<=n; j++)
			{ 
				p=(i-1)*n+j-1; 
				q=(j-1)*n+i-1;
				d=v[p]; 
				v[p]=v[q]; 
				v[q]=d;
			}
		}
	}

	/**
	 * �ڲ�������SplitUV�������
	 */
	private void sss(double[] fg, double[] cs)
	{ 
		double r,d;
	    
		if ((Math.abs(fg[0])+Math.abs(fg[1]))==0.0)
	    { 
			cs[0]=1.0; 
			cs[1]=0.0; 
			d=0.0;
		}
	    else 
	    { 
			d=Math.sqrt(fg[0]*fg[0]+fg[1]*fg[1]);
	        if (Math.abs(fg[0])>Math.abs(fg[1]))
	        { 
				d=Math.abs(d);
	            if (fg[0]<0.0) 
					d=-d;
	        }
	        if (Math.abs(fg[1])>=Math.abs(fg[0]))
	        { 
				d=Math.abs(d);
	            if (fg[1]<0.0) 
					d=-d;
	        }
	        
			cs[0]=fg[0]/d; 
			cs[1]=fg[1]/d;
	    }
	    
		r=1.0;
	    if (Math.abs(fg[0])>Math.abs(fg[1])) 
			r=cs[1];
	    else if (cs[0]!=0.0) 
			r=1.0/cs[0];

	    fg[0]=d; 
		fg[1]=r;
	}

	/**
	 * ������������ֵ�ֽⷨ���ֽ�ɹ���ԭ����Խ���Ԫ�ؾ��Ǿ��������ֵ
	 * 
	 * @param mtxAP - ����ԭ����Ĺ��������
	 * @param mtxU - ���طֽ���U����
	 * @param mtxV - ���طֽ���V����
	 * @param eps - ���㾫��
	 * @return boolean�ͣ�����Ƿ�ɹ�
	 */
	public boolean invertUV(Matrix mtxAP, Matrix mtxU, Matrix mtxV, double eps)
	{ 
		int i,j,k,l,t,p,q,f;

		// ��������ֵ�ֽ�
	    if (! splitUV(mtxU, mtxV, eps))
			return false;

		int m = numRows;
		int n = numColumns;

		// ��ʼ�����������
		if (! mtxAP.init(n, m))
			return false;

		// ������������

	    j=n;
	    if (m<n) 
			j=m;
	    j=j-1;
	    k=0;
	    while ((k<=j) && (elements[k*n+k]!=0.0)) 
			k=k+1;

	    k=k-1;
	    for (i=0; i<=n-1; i++)
		{
			for (j=0; j<=m-1; j++)
			{ 
				t=i*m+j;	
				mtxAP.elements[t]=0.0;
				for (l=0; l<=k; l++)
				{ 
					f=l*n+i; 
					p=j*m+l; 
					q=l*n+l;
					mtxAP.elements[t]=mtxAP.elements[t]+mtxV.elements[f]*mtxU.elements[p]/elements[q];
				}
			}
		}

	    return true;
	}

	/**
	 * Լ���Գƾ���Ϊ�Գ���Խ���ĺ�˹�ɶ�±任��
	 * 
	 * @param mtxQ - ���غ�˹�ɶ�±任�ĳ˻����Q
	 * @param mtxT - ������õĶԳ���Խ���
	 * @param dblB - һά���飬����Ϊ����Ľ���ضԳ���Խ�������Խ���Ԫ��
	 * @param dblC - һά���飬����Ϊ����Ľ���ǰn-1��Ԫ�ط��ضԳ���Խ����
	 *               �ζԽ���Ԫ��
	 * @return boolean�ͣ�����Ƿ�ɹ�
	 */
	public boolean makeSymTri(Matrix mtxQ, Matrix mtxT, double[] dblB, double[] dblC)
	{ 
		int i,j,k,u;
	    double h,f,g,h2;
	    
		// ��ʼ������Q��T
		if (! mtxQ.init(numColumns, numColumns) ||
			! mtxT.init(numColumns, numColumns))
			return false;

		if (dblB == null || dblC == null)
			return false;

		for (i=0; i<=numColumns-1; i++)
		{
			for (j=0; j<=numColumns-1; j++)
			{ 
				u=i*numColumns+j; 
				mtxQ.elements[u]=elements[u];
			}
		}

	    for (i=numColumns-1; i>=1; i--)
	    { 
			h=0.0;
	        if (i>1)
			{
				for (k=0; k<=i-1; k++)
	            { 
					u=i*numColumns+k; 
					h=h+mtxQ.elements[u]*mtxQ.elements[u];
				}
			}

	        if (h == 0.0)
	        { 
				dblC[i]=0.0;
	            if (i==1) 
					dblC[i]=mtxQ.elements[i*numColumns+i-1];
	            dblB[i]=0.0;
	        }
	        else
	        { 
				dblC[i]=Math.sqrt(h);
	            u=i*numColumns+i-1;
	            if (mtxQ.elements[u]>0.0) 
					dblC[i]=-dblC[i];

	            h=h-mtxQ.elements[u]*dblC[i];
	            mtxQ.elements[u]=mtxQ.elements[u]-dblC[i];
	            f=0.0;
	            for (j=0; j<=i-1; j++)
	            { 
					mtxQ.elements[j*numColumns+i]=mtxQ.elements[i*numColumns+j]/h;
	                g=0.0;
	                for (k=0; k<=j; k++)
						g=g+mtxQ.elements[j*numColumns+k]*mtxQ.elements[i*numColumns+k];

					if (j+1<=i-1)
						for (k=j+1; k<=i-1; k++)
							g=g+mtxQ.elements[k*numColumns+j]*mtxQ.elements[i*numColumns+k];

	                dblC[j]=g/h;
	                f=f+g*mtxQ.elements[j*numColumns+i];
	            }
	            
				h2=f/(h+h);
	            for (j=0; j<=i-1; j++)
	            { 
					f=mtxQ.elements[i*numColumns+j];
	                g=dblC[j]-h2*f;
	                dblC[j]=g;
	                for (k=0; k<=j; k++)
	                { 
						u=j*numColumns+k;
	                    mtxQ.elements[u]=mtxQ.elements[u]-f*dblC[k]-g*mtxQ.elements[i*numColumns+k];
	                }
	            }
	            
				dblB[i]=h;
	        }
	    }
	    
		for (i=0; i<=numColumns-2; i++) 
			dblC[i]=dblC[i+1];
	    
		dblC[numColumns-1]=0.0;
	    dblB[0]=0.0;
	    for (i=0; i<=numColumns-1; i++)
	    { 
			if ((dblB[i]!=(double)0.0) && (i-1>=0))
			{
				for (j=0; j<=i-1; j++)
	            { 
					g=0.0;
					for (k=0; k<=i-1; k++)
						g=g+mtxQ.elements[i*numColumns+k]*mtxQ.elements[k*numColumns+j];

					for (k=0; k<=i-1; k++)
	                { 
						u=k*numColumns+j;
						mtxQ.elements[u]=mtxQ.elements[u]-g*mtxQ.elements[k*numColumns+i];
	                }
	            }
			}

	        u=i*numColumns+i;
	        dblB[i]=mtxQ.elements[u]; mtxQ.elements[u]=1.0;
	        if (i-1>=0)
			{
				for (j=0; j<=i-1; j++)
	            { 
					mtxQ.elements[i*numColumns+j]=0.0; 
					mtxQ.elements[j*numColumns+i]=0.0;
				}
			}
	    }

	    // ����Գ���ԽǾ���
	    for (i=0; i<numColumns; ++i)
		{
		    for (j=0; j<numColumns; ++j)
			{
	            mtxT.setElement(i, j, 0);
	            k = i - j;
	            if (k == 0) 
		            mtxT.setElement(i, j, dblB[j]);
				else if (k == 1)
		            mtxT.setElement(i, j, dblC[j]);
				else if (k == -1)
		            mtxT.setElement(i, j, dblC[i]);
	        }
	    }

		return true;
	}

	/**
	 * ʵ�Գ���Խ����ȫ������ֵ�����������ļ���
	 * 
	 * @param dblB - һά���飬����Ϊ����Ľ�����Գ���Խ�������Խ���Ԫ�أ�
	 *			     ����ʱ���ȫ������ֵ��
	 * @param dblC - һά���飬����Ϊ����Ľ���ǰn-1��Ԫ�ش���Գ���Խ����
	 *               �ζԽ���Ԫ��
	 * @param mtxQ - ����뵥λ�����򷵻�ʵ�Գ���Խ��������ֵ��������
	 *			     �����MakeSymTri������õľ���A�ĺ�˹�ɶ�±任�ĳ˻�
	 *               ����Q���򷵻ؾ���A������ֵ�����������е�i��Ϊ������dblB
	 *               �е�j������ֵ��Ӧ������������
	 * @param nMaxIt - ������
	 * @param eps - ���㾫��
	 * @return boolean�ͣ�����Ƿ�ɹ�
	 */
	public boolean computeEvSymTri(double[] dblB, double[] dblC, Matrix mtxQ, int nMaxIt, double eps)
	{
		int i,j,k,m,it,u,v;
	    double d,f,h,g,p,r,e,s;
	    
		// ��ֵ
		int n = mtxQ.getNumColumns();
		dblC[n-1]=0.0; 
		d=0.0; 
		f=0.0;
	    
		// ������

		for (j=0; j<=n-1; j++)
	    { 
			it=0;
	        h=eps*(Math.abs(dblB[j])+Math.abs(dblC[j]));
	        if (h>d) 
				d=h;
	        
			m=j;
	        while ((m<=n-1) && (Math.abs(dblC[m])>d)) 
				m=m+1;
	        
			if (m!=j)
	        { 
				do
	            { 
					if (it==nMaxIt)
						return false;

	                it=it+1;
	                g=dblB[j];
	                p=(dblB[j+1]-g)/(2.0*dblC[j]);
	                r=Math.sqrt(p*p+1.0);
	                if (p>=0.0) 
						dblB[j]=dblC[j]/(p+r);
	                else 
						dblB[j]=dblC[j]/(p-r);
	                
					h=g-dblB[j];
	                for (i=j+1; i<=n-1; i++)
						dblB[i]=dblB[i]-h;
	                
					f=f+h; 
					p=dblB[m]; 
					e=1.0; 
					s=0.0;
	                for (i=m-1; i>=j; i--)
	                { 
						g=e*dblC[i]; 
						h=e*p;
	                    if (Math.abs(p)>=Math.abs(dblC[i]))
	                    { 
							e=dblC[i]/p; 
							r=Math.sqrt(e*e+1.0);
	                        dblC[i+1]=s*p*r; 
							s=e/r; 
							e=1.0/r;
	                    }
	                    else
						{ 
							e=p/dblC[i]; 
							r=Math.sqrt(e*e+1.0);
	                        dblC[i+1]=s*dblC[i]*r;
	                        s=1.0/r; 
							e=e/r;
	                    }
	                    
						p=e*dblB[i]-s*g;
	                    dblB[i+1]=h+s*(e*g+s*dblB[i]);
	                    for (k=0; k<=n-1; k++)
	                    { 
							u=k*n+i+1; 
							v=u-1;
	                        h=mtxQ.elements[u]; 
							mtxQ.elements[u]=s*mtxQ.elements[v]+e*h;
	                        mtxQ.elements[v]=e*mtxQ.elements[v]-s*h;
	                    }
	                }
	                
					dblC[j]=s*p; 
					dblB[j]=e*p;
	            
				} while (Math.abs(dblC[j])>d);
	        }
	        
			dblB[j]=dblB[j]+f;
	    }
	    
		for (i=0; i<=n-1; i++)
	    { 
			k=i; 
			p=dblB[i];
	        if (i+1<=n-1)
	        { 
				j=i+1;
	            while ((j<=n-1) && (dblB[j]<=p))
	            { 
					k=j; 
					p=dblB[j]; 
					j=j+1;
				}
	        }

	        if (k!=i)
	        { 
				dblB[k]=dblB[i]; 
				dblB[i]=p;
	            for (j=0; j<=n-1; j++)
	            { 
					u=j*n+i; 
					v=j*n+k;
	                p=mtxQ.elements[u]; 
					mtxQ.elements[u]=mtxQ.elements[v]; 
					mtxQ.elements[v]=p;
	            }
	        }
	    }
	    
		return true;
	}

	/**
	 * Լ��һ��ʵ����Ϊ���겮�����ĳ������Ʊ任��
	 */
	public void makeHberg()
	{ 
		int i = 0,j,k,u,v;
	    double d,t;

	    for (k=1; k<=numColumns-2; k++)
	    { 
			d=0.0;
	        for (j=k; j<=numColumns-1; j++)
	        { 
				u=j*numColumns+k-1; 
				t=elements[u];
	            if (Math.abs(t)>Math.abs(d))
	            { 
					d=t; 
					i=j;
				}
	        }
	        
			if (d != 0.0)
	        { 
				if (i!=k)
	            { 
					for (j=k-1; j<=numColumns-1; j++)
	                { 
						u=i*numColumns+j; 
						v=k*numColumns+j;
	                    t=elements[u]; 
						elements[u]=elements[v]; 
						elements[v]=t;
	                }
	                
					for (j=0; j<=numColumns-1; j++)
	                { 
						u=j*numColumns+i; 
						v=j*numColumns+k;
	                    t=elements[u]; 
						elements[u]=elements[v]; 
						elements[v]=t;
	                }
	            }
	            
				for (i=k+1; i<=numColumns-1; i++)
	            { 
					u=i*numColumns+k-1; 
					t=elements[u]/d; 
					elements[u]=0.0;
	                for (j=k; j<=numColumns-1; j++)
	                { 
						v=i*numColumns+j;
	                    elements[v]=elements[v]-t*elements[k*numColumns+j];
	                }
	                
					for (j=0; j<=numColumns-1; j++)
	                { 
						v=j*numColumns+k;
	                    elements[v]=elements[v]+t*elements[j*numColumns+i];
	                }
	            }
	        }
	    }
	}

	/**
	 * ����겮�����ȫ������ֵ��QR����
	 * 
	 * @param dblU - һά���飬����Ϊ����Ľ����ʱ�������ֵ��ʵ��
	 * @param dblV - һά���飬����Ϊ����Ľ����ʱ�������ֵ���鲿
	 * @param nMaxIt - ������
	 * @param eps - ���㾫��
	 * @return boolean�ͣ�����Ƿ�ɹ�
	 */
	public boolean computeEvHBerg(double[] dblU, double[] dblV, int nMaxIt, double eps)
	{ 
		int m,it,i,j,k,l,ii,jj,kk,ll;
	    double b,c,w,g,xy,p,q,r,x,s,e,f,z,y;
	    
		int n = numColumns;

		it=0; 
		m=n;
	    while (m!=0)
	    { 
			l=m-1;
	        while ((l>0) && (Math.abs(elements[l*n+l-1]) > 
					eps*(Math.abs(elements[(l-1)*n+l-1])+Math.abs(elements[l*n+l])))) 
			  l=l-1;

	        ii=(m-1)*n+m-1; 
			jj=(m-1)*n+m-2;
	        kk=(m-2)*n+m-1; 
			ll=(m-2)*n+m-2;
	        if (l==m-1)
	        { 
				dblU[m-1]=elements[(m-1)*n+m-1]; 
				dblV[m-1]=0.0;
	            m=m-1; 
				it=0;
	        }
	        else if (l==m-2)
	        { 
				b=-(elements[ii]+elements[ll]);
	            c=elements[ii]*elements[ll]-elements[jj]*elements[kk];
	            w=b*b-4.0*c;
	            y=Math.sqrt(Math.abs(w));
	            if (w>0.0)
	            { 
					xy=1.0;
	                if (b<0.0) 
						xy=-1.0;
	                dblU[m-1]=(-b-xy*y)/2.0;
	                dblU[m-2]=c/dblU[m-1];
	                dblV[m-1]=0.0; dblV[m-2]=0.0;
	            }
	            else
	            { 
					dblU[m-1]=-b/2.0; 
					dblU[m-2]=dblU[m-1];
	                dblV[m-1]=y/2.0; 
					dblV[m-2]=-dblV[m-1];
	            }
	            
				m=m-2; 
				it=0;
	        }
	        else
	        { 
				if (it>=nMaxIt)
					return false;

	            it=it+1;
	            for (j=l+2; j<=m-1; j++)
					elements[j*n+j-2]=0.0;
	            for (j=l+3; j<=m-1; j++)
					elements[j*n+j-3]=0.0;
	            for (k=l; k<=m-2; k++)
	            { 
					if (k!=l)
	                { 
						p=elements[k*n+k-1]; 
						q=elements[(k+1)*n+k-1];
	                    r=0.0;
	                    if (k!=m-2) 
							r=elements[(k+2)*n+k-1];
	                }
	                else
	                { 
						x=elements[ii]+elements[ll];
	                    y=elements[ll]*elements[ii]-elements[kk]*elements[jj];
	                    ii=l*n+l; 
						jj=l*n+l+1;
	                    kk=(l+1)*n+l; 
						ll=(l+1)*n+l+1;
	                    p=elements[ii]*(elements[ii]-x)+elements[jj]*elements[kk]+y;
	                    q=elements[kk]*(elements[ii]+elements[ll]-x);
	                    r=elements[kk]*elements[(l+2)*n+l+1];
	                }
	                
					if ((Math.abs(p)+Math.abs(q)+Math.abs(r))!=0.0)
	                { 
						xy=1.0;
	                    if (p<0.0) 
							xy=-1.0;
	                    s=xy*Math.sqrt(p*p+q*q+r*r);
	                    if (k!=l) 
							elements[k*n+k-1]=-s;
	                    e=-q/s; 
						f=-r/s; 
						x=-p/s;
	                    y=-x-f*r/(p+s);
	                    g=e*r/(p+s);
	                    z=-x-e*q/(p+s);
	                    for (j=k; j<=m-1; j++)
	                    { 
							ii=k*n+j; 
							jj=(k+1)*n+j;
	                        p=x*elements[ii]+e*elements[jj];
	                        q=e*elements[ii]+y*elements[jj];
	                        r=f*elements[ii]+g*elements[jj];
	                        if (k!=m-2)
	                        { 
								kk=(k+2)*n+j;
	                            p=p+f*elements[kk];
	                            q=q+g*elements[kk];
	                            r=r+z*elements[kk]; 
								elements[kk]=r;
	                        }
	                        
							elements[jj]=q; elements[ii]=p;
	                    }
	                    
						j=k+3;
	                    if (j>=m-1) 
							j=m-1;
	                    
						for (i=l; i<=j; i++)
	                    { 
							ii=i*n+k; 
							jj=i*n+k+1;
	                        p=x*elements[ii]+e*elements[jj];
	                        q=e*elements[ii]+y*elements[jj];
	                        r=f*elements[ii]+g*elements[jj];
	                        if (k!=m-2)
	                        { 
								kk=i*n+k+2;
	                            p=p+f*elements[kk];
	                            q=q+g*elements[kk];
	                            r=r+z*elements[kk]; 
								elements[kk]=r;
	                        }
	                        
							elements[jj]=q; 
							elements[ii]=p;
	                    }
	                }
	            }
	        }
	    }
	    
		return true;
	}

	/**
	 * ��ʵ�Գƾ�������ֵ�������������ſɱȷ�
	 * 
	 * @param dblEigenValue - һά���飬����Ϊ����Ľ����ʱ�������ֵ
	 * @param mtxEigenVector - ����ʱ������������������е�i��Ϊ������
	 *                         dblEigenValue�е�j������ֵ��Ӧ����������
	 * @param nMaxIt - ������
	 * @param eps - ���㾫��
	 * @return boolean�ͣ�����Ƿ�ɹ�
	 */
	public boolean computeEvJacobi(double[] dblEigenValue, Matrix mtxEigenVector, int nMaxIt, double eps)
	{ 
		int i,j,p = 0,q = 0,u,w,t,s,l;
	    double fm,cn,sn,omega,x,y,d;
	    
		if (! mtxEigenVector.init(numColumns, numColumns))
			return false;

		l=1;
	    for (i=0; i<=numColumns-1; i++)
	    { 
			mtxEigenVector.elements[i*numColumns+i]=1.0;
	        for (j=0; j<=numColumns-1; j++)
				if (i!=j) 
					mtxEigenVector.elements[i*numColumns+j]=0.0;
	    }
	    
		while (true)
	    { 
			fm=0.0;
	        for (i=1; i<=numColumns-1; i++)
			{
				for (j=0; j<=i-1; j++)
				{ 
					d=Math.abs(elements[i*numColumns+j]);
					if ((i!=j) && (d>fm))
					{ 
						fm=d; 
						p=i; 
						q=j;
					}
				}
			}

	        if (fm<eps)
			{
				for (i=0; i<numColumns; ++i)
					dblEigenValue[i] = getElement(i,i);
				return true;
			}

	        if (l>nMaxIt)  
				return false;
	        
			l=l+1;
	        u=p*numColumns+q; 
			w=p*numColumns+p; 
			t=q*numColumns+p; 
			s=q*numColumns+q;
	        x=-elements[u]; 
			y=(elements[s]-elements[w])/2.0;
	        omega=x/Math.sqrt(x*x+y*y);

	        if (y<0.0) 
				omega=-omega;

	        sn=1.0+Math.sqrt(1.0-omega*omega);
	        sn=omega/Math.sqrt(2.0*sn);
	        cn=Math.sqrt(1.0-sn*sn);
	        fm=elements[w];
	        elements[w]=fm*cn*cn+elements[s]*sn*sn+elements[u]*omega;
	        elements[s]=fm*sn*sn+elements[s]*cn*cn-elements[u]*omega;
	        elements[u]=0.0; 
			elements[t]=0.0;
	        for (j=0; j<=numColumns-1; j++)
			{
				if ((j!=p) && (j!=q))
				{ 
					u=p*numColumns+j; w=q*numColumns+j;
					fm=elements[u];
					elements[u]=fm*cn+elements[w]*sn;
					elements[w]=-fm*sn+elements[w]*cn;
				}
			}

	        for (i=0; i<=numColumns-1; i++)
			{
				if ((i!=p) && (i!=q))
	            { 
					u=i*numColumns+p; 
					w=i*numColumns+q;
					fm=elements[u];
					elements[u]=fm*cn+elements[w]*sn;
					elements[w]=-fm*sn+elements[w]*cn;
	            }
			}

	        for (i=0; i<=numColumns-1; i++)
	        { 
				u=i*numColumns+p; 
				w=i*numColumns+q;
	            fm=mtxEigenVector.elements[u];
	            mtxEigenVector.elements[u]=fm*cn+mtxEigenVector.elements[w]*sn;
	            mtxEigenVector.elements[w]=-fm*sn+mtxEigenVector.elements[w]*cn;
	        }
	    }
	}

	/**
	 * ��ʵ�Գƾ�������ֵ�������������ſɱȹ�ط�
	 * 
	 * @param dblEigenValue - һά���飬����Ϊ����Ľ����ʱ�������ֵ
	 * @param mtxEigenVector - ����ʱ������������������е�i��Ϊ������
	 *                         dblEigenValue�е�j������ֵ��Ӧ����������
	 * @param eps - ���㾫��
	 * @return boolean�ͣ�����Ƿ�ɹ�
	 */
	public boolean computeEvJacobi(double[] dblEigenValue, Matrix mtxEigenVector, double eps)
	{ 
		int i,j,p,q,u,w,t,s;
	    double ff,fm,cn,sn,omega,x,y,d;
	    
		if (! mtxEigenVector.init(numColumns, numColumns))
			return false;

		for (i=0; i<=numColumns-1; i++)
	    { 
			mtxEigenVector.elements[i*numColumns+i]=1.0;
	        for (j=0; j<=numColumns-1; j++)
				if (i!=j) 
					mtxEigenVector.elements[i*numColumns+j]=0.0;
	    }
	    
		ff=0.0;
	    for (i=1; i<=numColumns-1; i++)
		{
			for (j=0; j<=i-1; j++)
			{ 
				d=elements[i*numColumns+j]; 
				ff=ff+d*d; 
			}
		}

	    ff=Math.sqrt(2.0*ff);
		ff=ff/(1.0*numColumns);

		boolean nextLoop = false;
		while (true)
		{
			for (i=1; i<=numColumns-1; i++)
			{
				for (j=0; j<=i-1; j++)
				{ 
					d=Math.abs(elements[i*numColumns+j]);
					if (d>ff)
					{ 
						p=i; 
						q=j;

						u=p*numColumns+q; 
						w=p*numColumns+p; 
						t=q*numColumns+p; 
						s=q*numColumns+q;
						x=-elements[u]; 
						y=(elements[s]-elements[w])/2.0;
						omega=x/Math.sqrt(x*x+y*y);
						if (y<0.0) 
							omega=-omega;
					    
						sn=1.0+Math.sqrt(1.0-omega*omega);
						sn=omega/Math.sqrt(2.0*sn);
						cn=Math.sqrt(1.0-sn*sn);
						fm=elements[w];
						elements[w]=fm*cn*cn+elements[s]*sn*sn+elements[u]*omega;
						elements[s]=fm*sn*sn+elements[s]*cn*cn-elements[u]*omega;
						elements[u]=0.0; elements[t]=0.0;
					    
						for (j=0; j<=numColumns-1; j++)
						{
							if ((j!=p)&&(j!=q))
							{ 
								u=p*numColumns+j; 
								w=q*numColumns+j;
								fm=elements[u];
								elements[u]=fm*cn+elements[w]*sn;
								elements[w]=-fm*sn+elements[w]*cn;
							}
						}

						for (i=0; i<=numColumns-1; i++)
						{
							if ((i!=p)&&(i!=q))
							{ 
								u=i*numColumns+p; 
								w=i*numColumns+q;
								fm=elements[u];
								elements[u]=fm*cn+elements[w]*sn;
								elements[w]=-fm*sn+elements[w]*cn;
							}
						}
					    
						for (i=0; i<=numColumns-1; i++)
						{ 
							u=i*numColumns+p; 
							w=i*numColumns+q;
							fm=mtxEigenVector.elements[u];
							mtxEigenVector.elements[u]=fm*cn+mtxEigenVector.elements[w]*sn;
							mtxEigenVector.elements[w]=-fm*sn+mtxEigenVector.elements[w]*cn;
						}

						nextLoop = true;
						break;
					}
				}

				if (nextLoop)
					break;
			}
		        
			if (nextLoop)
			{
				nextLoop = false;
				continue;
			}

			nextLoop = false;

			// ���ﵽ����Ҫ���˳�ѭ�������ؽ��
			if (ff<eps) 
			{
				for (i=0; i<numColumns; ++i)
					dblEigenValue[i] = getElement(i,i);
				return true;
			}
		    
			ff=ff/(1.0*numColumns);
		}
	}
}
