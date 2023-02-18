import pandas as pd
import numpy as np
from pathlib import Path
import re
from jamo import h2j, j2hcj

kormsg = "-가(01)/반대-(), 왜룁옓"

kor_re = re.compile('[ㄱ-힣]+')

msg = "".join(kor_re.findall(kormsg))

print(j2hcj(h2j(msg)))