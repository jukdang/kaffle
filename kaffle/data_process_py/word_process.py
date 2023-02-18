import pandas as pd
import numpy as np
from pathlib import Path
import re
from jamo import h2j, j2hcj
from time import sleep
import sqlite3

# df1 = None
# for kor_dict_path in Path('./kor_dict').glob('kor*.csv'):
#     df_csv = pd.read_csv(kor_dict_path)
#     df1 = pd.concat([df1,df_csv])

# df1.to_csv('./kor_dict/all_kor01.csv', index=False)

# #---------------------------------------------------------

# kor_re = re.compile('[ㄱ-힣]+')

# df2 = pd.read_csv('./kor_dict/all_kor01.csv', low_memory=False)

# df2['어휘_re'] = df2['어휘'].str.findall(kor_re).str.join('')

# df2.to_csv('./kor_dict/all_kor02.csv', index=False)

# #---------------------------------------------------------

# df3 = pd.read_csv('./kor_dict/all_kor02.csv', low_memory=False)

# df3['어휘_re'] = df3['어휘_re'].astype(str)
# df3 = df3[df3['어휘_re']!='nan']

# df3['어휘_자모'] = df3['어휘_re'].apply(lambda x : j2hcj(h2j(x)))
# df3['어휘_자모_size'] = df3['어휘_자모'].apply(lambda x : len(x))

# df3.to_csv('./kor_dict/all_kor03.csv', index=False)

# #---------------------------------------------------------

# df4 = pd.read_csv('./kor_dict/all_kor03.csv', low_memory=False)

# df4 = df4[df4['어휘_자모_size']==5]
# df4 = df4[df4['어휘_자모'].str[-1].str.match(pat='[ㅏ-ㅣ]')==False] 

# df4.to_csv('./kor_dict/all_kor04.csv', index=False)

#---------------------------------------------------------

# df5 = pd.read_csv('./kor_dict/all_kor04.csv', low_memory=False)

# df5 = df5[['어휘', '구성 단위', '고유어 여부', '품사', '뜻풀이', '범주', '어휘_re', '어휘_자모', '어휘_자모_size']]
# print(df5.head(5))
# print(len(df5))
# print(df5.info())

# df5.to_csv('./kor_dict/all_kor05.csv', index=False)

#---------------------------------------------------------

# df6 = pd.read_csv('./kor_dict/all_kor05.csv')

# con = sqlite3.connect('kor_dict.db')

# df6.to_sql('words', con, if_exists='replace', index_label='id')

